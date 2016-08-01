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


public class SignInUseCase extends UseCase {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AuthService authService;

    @Inject
    public SignInUseCase(ThreadExecutor executor,
                         PostExecutionThread postExecutionThread,
                         AuthService authService) {
        super(executor, postExecutionThread);
        this.authService = authService;
    }

    public Observable<Boolean> execute(String username, String password) {
        return buildObservable(username, password)
                .subscribeOn(Schedulers.from(executor))
                .observeOn(postExecutionThread.getScheduler());
    }

    private Observable<Boolean> buildObservable(String username, String password) {
        AsyncSubject<Boolean> subject = AsyncSubject.create();
        authService.login(username, password).subscribe((v) -> {
            subject.onNext(true);
            subject.onCompleted();
        }, (e) -> {
            subject.onError(e);
        });
        return subject;
    }

}