package com.tonitealive.app.internal.di.modules

import com.tonitealive.app.domain.executor.PostExecutionThread
import com.tonitealive.app.domain.executor.ThreadExecutor
import com.tonitealive.app.domain.interactors.SignUpUseCase
import com.tonitealive.app.domain.repositories.UsersRepository
import com.tonitealive.app.domain.service.AuthService
import com.tonitealive.app.internal.di.annotations.PerActivity
import com.tonitealive.app.ui.presenters.signup.DefaultSignUpPresenter
import com.tonitealive.app.ui.presenters.signup.SignUpPresenter
import com.tonitealive.app.ui.views.signup.SignUpView
import dagger.Module
import dagger.Provides

@Module
open class SignUpModule(private val view: SignUpView) {

    @Provides @PerActivity
    open fun provideSignUpUseCase(executor: ThreadExecutor,
                                  postExecutionThread: PostExecutionThread,
                                  usersRepository: UsersRepository,
                                  authService: AuthService): SignUpUseCase {
        return SignUpUseCase(executor, postExecutionThread, usersRepository, authService)
    }

    @Provides @PerActivity
    open fun provideSignUpPresenter(signUpUseCase: SignUpUseCase): SignUpPresenter {
        return DefaultSignUpPresenter(view, signUpUseCase)
    }

}