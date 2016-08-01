package com.tonitealive.app.data.net;

import com.tonitealive.app.data.TokenStore;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockWebServer;


public class RetrofitToniteAliveApiTest {

    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock private TokenStore mockTokenStore;

    private MockWebServer server;
    private RetrofitToniteAliveApi api;
    private HttpUrl baseUrl;

    @Before
    void setup() throws Exception {
        // Setup the server
        server = new MockWebServer();
        server.start();
        baseUrl = server.url("");
        api = new RetrofitToniteAliveApi(baseUrl.toString(), mockTokenStore);
    }

    @After
    void tearDown() throws Exception {
        server.shutdown();
    }

}