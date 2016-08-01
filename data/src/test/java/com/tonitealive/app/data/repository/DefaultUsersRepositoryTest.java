package com.tonitealive.app.data.repository;

import com.tonitealive.app.data.net.ToniteAliveApi;

import org.junit.Before;
import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


public class DefaultUsersRepositoryTest {

    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock private ToniteAliveApi mockApi;

    private DefaultUsersRepository usersRepository;

    @Before
    void setup() {
        usersRepository = new DefaultUsersRepository(mockApi);
    }

}