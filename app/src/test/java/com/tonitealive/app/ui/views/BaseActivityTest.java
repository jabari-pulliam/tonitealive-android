package com.tonitealive.app.ui.views;

import android.app.Application;

import com.tonitealive.app.BuildConfig;
import com.tonitealive.app.TestConstants;
import com.tonitealive.app.ToniteAliveApplication;
import com.tonitealive.app.internal.di.components.ApplicationComponent;
import com.tonitealive.app.internal.di.components.DaggerApplicationComponent;
import com.tonitealive.app.internal.di.modules.ApplicationModule;
import com.tonitealive.app.ui.Navigator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = TestConstants.SDK_VERSION)
public class BaseActivityTest {

    @Rule
    public final MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock private Navigator mockNavigator;

    @Before
    public void setup() {
        ToniteAliveApplication application = (ToniteAliveApplication) RuntimeEnvironment.application;
        ApplicationComponent component = DaggerApplicationComponent.builder()
                                            .applicationModule(new TestApplicationModule(RuntimeEnvironment.application))
                                            .build();


    }

    private class TestApplicationModule extends ApplicationModule {

        TestApplicationModule(Application application) {
            super(application);
        }

        @Override
        public Navigator provideNavigator() {
            return mockNavigator;
        }
    }

    private class MyTestActivity extends BaseActivity {

        public Navigator getNavigator() {
            return super.getNavigator();
        }

    }

}
