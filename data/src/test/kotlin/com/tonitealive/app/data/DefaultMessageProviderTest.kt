package com.tonitealive.app.data

import com.tonitealive.app.SDK_VERSION
import com.tonitealive.app.domain.MessageId
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(SDK_VERSION))
class DefaultMessageProviderTest {

    lateinit var messageProvider: DefaultMessageProvider

    @Before
    fun setup() {
        messageProvider = DefaultMessageProvider(RuntimeEnvironment.application)
    }

    @After
    fun teardown() {
        Robolectric.reset()
    }

    @Test
    fun getMessage_shouldReturnMessage() {
        // With
        val messageId = MessageId.SIGN_IN_SUCCESS
        val messageCode = R.string.signin_succeeded
        val targetMessage = RuntimeEnvironment.application.getString(messageCode)

        // When
        val message = messageProvider.getMessage(messageId)

        //  Then
        assertThat(message).isEqualTo(targetMessage)
    }

}