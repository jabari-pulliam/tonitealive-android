package com.tonitealive.app.data.net

import com.tonitealive.app.data.TokenStore
import com.tonitealive.app.domain.model.AuthToken
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit

class ApiAuthInterceptorTest {

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Mock private lateinit var mockTokenStore: TokenStore
    @Mock private lateinit var mockChain: Interceptor.Chain

    private lateinit var client: OkHttpClient
    private lateinit var authInterceptor: ApiAuthInterceptor
    private lateinit var mockWebServer: MockWebServer
    private lateinit var serverUrl: HttpUrl

    @Before
    fun setup() {
        authInterceptor = ApiAuthInterceptor(mockTokenStore)

        // Setup the server
        mockWebServer = MockWebServer()
        mockWebServer.start()
        mockWebServer.enqueue(MockResponse())
        serverUrl = mockWebServer.url("")

        // Setup the client
        client = OkHttpClient.Builder()
                    .addInterceptor(authInterceptor)
                    .build()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun intercept_withToken_shouldAddTokenToAuthHeader() {
        // With
        val token = "123456"
        val authToken = AuthToken(1L, "password", token, "foo")
        val request = Request.Builder()
                        .url(serverUrl)
                        .get()
                        .build()

        // When
        Mockito.`when`(mockTokenStore.authToken).thenReturn(authToken)
        client.newCall(request).execute()
        val recordedRequest = mockWebServer.takeRequest()

        // Then
        assertThat(recordedRequest.getHeader("Authorization")).isEqualTo("Bearer ${authToken.accessToken}")
    }

    @Test
    fun intercept_withoutToken_shouldNotAddTokenToAuthHeader() {
        // With
        val request = Request.Builder()
                .url(serverUrl)
                .get()
                .build()

        // When
        Mockito.`when`(mockTokenStore.authToken).thenReturn(null)
        client.newCall(request).execute()
        val recordedRequest = mockWebServer.takeRequest()

        // Then
        assertThat(recordedRequest.getHeader("Authorization")).isNull()
    }

}