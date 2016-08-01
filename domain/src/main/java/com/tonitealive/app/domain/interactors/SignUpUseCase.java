package com.tonitealive.app.domain.interactors;

import com.tonitealive.app.domain.executor.PostExecutionThread;
import com.tonitealive.app.domain.executor.ThreadExecutor;
import com.tonitealive.app.domain.service.AuthService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.AsyncSubject;

public class SignUpUseCase extends UseCase {

    private final AuthService authService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    public SignUpUseCase(ThreadExecutor executor, PostExecutionThread postExecutionThread, AuthService authService) {
        super(executor, postExecutionThread);
        this.authService = authService;
    }

    public Observable<Void> execute(String username, String email, String password) {
        return buildObservable(username, email, password)
                .subscribeOn(Schedulers.from(executor))
                .observeOn(postExecutionThread.getScheduler());
    }

    private Observable<Void> buildObservable(String username, String email, String password) {
        AsyncSubject<Void> subject = AsyncSubject.create();
        authService.register(username, email, password, null, null).subscribe((v) -> {
            subject.onNext(null);
            subject.onCompleted();
        }, (e) -> {
            logger.error("Sign up failed", e);
            subject.onError(e);
        });
        return subject;
    }

}
