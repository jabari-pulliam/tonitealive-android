package com.tonitealive.app.data;

import com.tonitealive.app.TestConstants;
import com.tonitealive.app.domain.MessageId;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = {TestConstants.SDK_VERSION})
public class DefaultMessageProviderTest {

    private DefaultMessageProvider messageProvider;

    public DefaultMessageProviderTest(){}

    @Before
    public void setup() {
        messageProvider = new DefaultMessageProvider(RuntimeEnvironment.application);
    }

    @After
    public void teardown() {
        Robolectric.reset();
    }

    @Test
    public void getMessage_shouldReturnMessage() {
        // With
        MessageId messageId = MessageId.SIGN_IN_SUCCESS;
        int messageCode = R.string.signin_succeeded;
        String targetMessage = RuntimeEnvironment.application.getString(messageCode);

        // When
        String message = messageProvider.getMessage(messageId);

        //  Then
        assertThat(message).isEqualTo(targetMessage);
    }

}