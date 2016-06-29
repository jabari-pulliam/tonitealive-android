package com.tonitealive.app.internal.di.modules

import com.tonitealive.app.ui.presenters.signin.DefaultSignInPresenter
import com.tonitealive.app.ui.presenters.signin.SignInPresenter
import com.tonitealive.app.ui.views.signin.SignInView
import dagger.Module
import dagger.Provides

@Module
open class SignInModule(val signInView: SignInView) {

    @Provides
    open fun provideView(): SignInView {
        return signInView
    }

    @Provides
    open fun providePresenter(): SignInPresenter {
        return DefaultSignInPresenter(signInView)
    }

}