package com.tonitealive.app.data.net;

import com.google.common.base.Optional;
import com.tonitealive.app.data.TokenStore;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiAuthInterceptorTest {

    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock private TokenStore mockTokenStore;
    @Mock private Interceptor.Chain mockChain;

    private OkHttpClient client;
    private ApiAuthInterceptor authInterceptor;
    private MockWebServer mockWebServer;
    private HttpUrl serverUrl;

    public ApiAuthInterceptorTest(){}

    @Before
    public void setup() throws IOException {
        authInterceptor = new ApiAuthInterceptor(mockTokenStore);

        // Setup the server
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        mockWebServer.enqueue(new MockResponse());
        serverUrl = mockWebServer.url("");

        // Setup the client
        client = new OkHttpClient.Builder()
                    .addInterceptor(authInterceptor)
                    .build();
    }

    @After
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void intercept_withToken_shouldAddTokenToAuthHeader() throws Exception {
        // With
        String token = "123456";
        Request request = new Request.Builder()
                        .url(serverUrl)
                        .get()
                        .build();

        // When
        Mockito.when(mockTokenStore.getAuthToken()).thenReturn(Optional.of(token));
        client.newCall(request).execute();
        RecordedRequest recordedRequest = mockWebServer.takeRequest();

        // Then
        assertThat(recordedRequest.getHeader("Authorization")).isEqualTo("Bearer " + token);
    }

    @Test
    public void intercept_withoutToken_shouldNotAddTokenToAuthHeader() throws Exception {
        // With
        Request request = new Request.Builder()
                .url(serverUrl)
                .get()
                .build();

        // When
        Mockito.when(mockTokenStore.getAuthToken()).thenReturn(Optional.absent());
        client.newCall(request).execute();
        RecordedRequest recordedRequest = mockWebServer.takeRequest();

        // Then
        assertThat(recordedRequest.getHeader("Authorization")).isNull();
    }

}