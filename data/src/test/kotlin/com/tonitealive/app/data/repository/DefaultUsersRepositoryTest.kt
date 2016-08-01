package com.tonitealive.app.data.repository

import com.tonitealive.app.data.net.ToniteAliveApi
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit


class DefaultUsersRepositoryTest {

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Mock lateinit var mockApi: ToniteAliveApi

    lateinit var usersRepository: DefaultUsersRepository

    @Before
    fun setup() {
        usersRepository = DefaultUsersRepository(mockApi)
    }

}