package com.tonitealive.app.data

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.tonitealive.app.SDK_VERSION
import com.tonitealive.app.domain.model.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(SDK_VERSION))
class DefaultUserCacheTest {

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var mockSerializer: JsonSerializer

    lateinit var sharedPreferences: SharedPreferences

    lateinit var userCache: DefaultUserCache

    @Before
    fun setup() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(RuntimeEnvironment.application)
        userCache = DefaultUserCache(mockSerializer, sharedPreferences)
    }

    @After
    fun tearDown() {
        Robolectric.reset()
    }

    @Test
    fun put_shouldPlaceItemInCache() {
        // With
        val username = "foo"
        val email = "foo@bar.com"
        val user = User(username, email)
        val serialized = "xyz"

        // When
        Mockito.`when`(mockSerializer.toString(user)).thenReturn(serialized)
        Mockito.`when`(mockSerializer.fromString(serialized, User::class.java)).thenReturn(user)
        userCache.put(user)

        // Then
        assertThat(userCache.isCached(username)).isTrue()
    }

}