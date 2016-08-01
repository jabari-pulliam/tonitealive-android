package com.tonitealive.app.internal.di.modules;

import com.tonitealive.app.domain.executor.PostExecutionThread;
import com.tonitealive.app.domain.executor.ThreadExecutor;
import com.tonitealive.app.domain.interactors.SignUpUseCase;
import com.tonitealive.app.domain.service.AuthService;
import com.tonitealive.app.internal.di.annotations.PerActivity;
import com.tonitealive.app.ui.presenters.signup.DefaultSignUpPresenter;
import com.tonitealive.app.ui.presenters.signup.SignUpPresenter;
import com.tonitealive.app.ui.views.signup.SignUpView;

import dagger.Module;
import dagger.Provides;

@Module
public class SignUpModule {

    private final SignUpView view;

    public SignUpModule(SignUpView view) {
        this.view = view;
    }

    @Provides @PerActivity
    public SignUpUseCase provideSignUpUseCase(ThreadExecutor executor,
                                  PostExecutionThread postExecutionThread,
                                  AuthService authService) {
        return new SignUpUseCase(executor, postExecutionThread, authService);
    }

    @Provides @PerActivity
    public SignUpPresenter provideSignUpPresenter(SignUpUseCase signUpUseCase) {
        return new DefaultSignUpPresenter(view, signUpUseCase);
    }

}