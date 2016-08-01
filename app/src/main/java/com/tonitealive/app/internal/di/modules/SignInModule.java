package com.tonitealive.app.internal.di.modules;

import com.tonitealive.app.domain.executor.PostExecutionThread;
import com.tonitealive.app.domain.executor.ThreadExecutor;
import com.tonitealive.app.domain.interactors.SignInUseCase;
import com.tonitealive.app.domain.service.AuthService;
import com.tonitealive.app.internal.di.annotations.PerActivity;
import com.tonitealive.app.ui.Navigator;
import com.tonitealive.app.ui.presenters.signin.DefaultSignInPresenter;
import com.tonitealive.app.ui.presenters.signin.SignInPresenter;
import com.tonitealive.app.ui.views.signin.SignInView;

import dagger.Module;
import dagger.Provides;

@Module
public class SignInModule {

    private final SignInView signInView;

    public SignInModule(SignInView signInView) {
        this.signInView = signInView;
    }

    @Provides
    @PerActivity
    public SignInView provideView() {
        return signInView;
    }

    @Provides
    @PerActivity
    public SignInPresenter providePresenter(SignInUseCase signInUseCase, Navigator navigator) {
        return new DefaultSignInPresenter(signInView, signInUseCase, navigator);
    }

    @Provides
    @PerActivity
    public SignInUseCase provideSignInUseCase(ThreadExecutor threadExecutor,
                             PostExecutionThread postExecutionThread,
                             AuthService authService) {
        return new SignInUseCase(threadExecutor, postExecutionThread, authService);
    }

}