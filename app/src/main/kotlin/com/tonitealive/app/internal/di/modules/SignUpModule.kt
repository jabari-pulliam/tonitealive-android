package com.tonitealive.app.internal.di.modules

import com.tonitealive.app.internal.di.annotations.PerActivity
import com.tonitealive.app.ui.presenters.signup.DefaultSignUpPresenter
import com.tonitealive.app.ui.presenters.signup.SignUpPresenter
import com.tonitealive.app.ui.views.signup.SignUpView
import dagger.Module
import dagger.Provides

@Module
open class SignUpModule(private val view: SignUpView) {

    @Provides @PerActivity
    open fun provideSignUpPresenter(): SignUpPresenter {
        return DefaultSignUpPresenter()
    }

}