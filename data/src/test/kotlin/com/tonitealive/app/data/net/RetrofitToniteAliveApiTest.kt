package com.tonitealive.app.data.net

import com.tonitealive.app.data.TokenStore
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit


class RetrofitToniteAliveApiTest {

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Mock private lateinit var mockTokenStore: TokenStore

    lateinit var server: MockWebServer
    lateinit var api: RetrofitToniteAliveApi
    lateinit var baseUrl: HttpUrl

    @Before
    fun setup() {
        // Setup the server
        server = MockWebServer()
        server.start()
        baseUrl = server.url("")
        api = RetrofitToniteAliveApi(baseUrl.toString(), mockTokenStore)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

}