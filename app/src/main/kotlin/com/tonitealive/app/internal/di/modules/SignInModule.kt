package com.tonitealive.app.internal.di.modules

import com.tonitealive.app.domain.executor.PostExecutionThread
import com.tonitealive.app.domain.executor.ThreadExecutor
import com.tonitealive.app.domain.interactors.SignInUseCase
import com.tonitealive.app.domain.service.AuthService
import com.tonitealive.app.internal.di.annotations.PerActivity
import com.tonitealive.app.ui.presenters.signin.DefaultSignInPresenter
import com.tonitealive.app.ui.presenters.signin.SignInPresenter
import com.tonitealive.app.ui.views.signin.SignInView
import dagger.Module
import dagger.Provides

@Module
open class SignInModule(private val signInView: SignInView) {

    @Provides
    @PerActivity
    open fun provideView(): SignInView {
        return signInView
    }

    @Provides
    @PerActivity
    open fun providePresenter(signInUseCase: SignInUseCase): SignInPresenter {
        return DefaultSignInPresenter(signInView, signInUseCase)
    }

    @Provides
    @PerActivity
    open fun provideSignInUseCase(threadExecutor: ThreadExecutor,
                             postExecutionThread: PostExecutionThread,
                             authService: AuthService): SignInUseCase {
        return SignInUseCase(threadExecutor, postExecutionThread, authService)
    }

}