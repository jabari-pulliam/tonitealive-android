package com.tonitealive.app.internal.di.modules

import com.tonitealive.app.internal.di.annotations.PerActivity
import com.tonitealive.app.ui.presenters.signin.DefaultSignInPresenter
import com.tonitealive.app.ui.presenters.signin.SignInPresenter
import com.tonitealive.app.ui.views.signin.SignInView
import dagger.Module
import dagger.Provides

@Module
open class SignInModule(val signInView: SignInView) {

    @Provides
    @PerActivity
    open fun provideView(): SignInView {
        return signInView
    }

    @Provides
    @PerActivity
    open fun providePresenter(): SignInPresenter {
        return DefaultSignInPresenter(signInView)
    }

}