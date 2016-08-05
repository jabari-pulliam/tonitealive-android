package com.tonitealive.app.ui.presenters.main;

import com.tonitealive.app.domain.executor.PostExecutionThread;
import com.tonitealive.app.domain.executor.ThreadExecutor;
import com.tonitealive.app.domain.service.AuthService;
import com.tonitealive.app.internal.di.annotations.PerActivity;
import com.tonitealive.app.ui.Navigator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import rx.schedulers.Schedulers;

@PerActivity
public class DefaultMainPresenter implements MainPresenter {

    private final Navigator navigator;
    private final AuthService authService;
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    public DefaultMainPresenter(Navigator navigator,
                                AuthService authService,
                                ThreadExecutor threadExecutor,
                                PostExecutionThread postExecutionThread) {
        this.navigator = navigator;
        this.authService = authService;
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    @Override
    public void onResume() {
        authService.getLoggedInUser()
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe((user) -> {
                    if (user == null) {
                        logger.info("No user signed in. Navigating to sign in view.");
                        navigator.goToSignInView();
                    }
                }, (error) -> {
                    logger.error("Failed to get logged in user", error);
                });
    }

}
