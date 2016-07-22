package com.tonitealive.app.domain.interactors

import com.tonitealive.app.domain.executor.PostExecutionThread
import com.tonitealive.app.domain.executor.ThreadExecutor
import com.tonitealive.app.domain.service.AuthService
import org.slf4j.LoggerFactory
import rx.Observable
import rx.lang.kotlin.AsyncSubject
import rx.schedulers.Schedulers
import javax.inject.Inject


class SignInUseCase @Inject constructor(executor: ThreadExecutor,
                                        postExecutionThread: PostExecutionThread,
                                        private val authService: AuthService) : UseCase(executor, postExecutionThread) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun execute(username: String, password: String): Observable<Boolean> {
        logger.debug("execute() called")
        return buildObservable(username, password)
                .subscribeOn(Schedulers.from(executor))
                .observeOn(postExecutionThread.scheduler)
    }

    fun buildObservable(username: String, password: String): Observable<Boolean> {
        val subject = AsyncSubject<Boolean>()
        authService.login(username, password).subscribe({ token ->
            logger.debug("Sign in succeeded")
            subject.onNext(true)
            subject.onCompleted()
        }, { error ->
            logger.error("Sign in failed", error)
            subject.onError(error)
        })
        return subject
    }

}