package com.tonitealive.app.domain.interactors

import com.tonitealive.app.domain.executor.PostExecutionThread
import com.tonitealive.app.domain.executor.ThreadExecutor
import com.tonitealive.app.domain.model.User
import com.tonitealive.app.domain.repositories.UsersRepository
import com.tonitealive.app.domain.service.AuthService
import org.slf4j.LoggerFactory
import rx.Observable
import rx.lang.kotlin.AsyncSubject
import rx.schedulers.Schedulers
import javax.inject.Inject

class SignUpUseCase @Inject constructor(executor: ThreadExecutor,
                                        postExecutionThread: PostExecutionThread,
                                        private val usersRepository: UsersRepository,
                                        private val authService: AuthService) : UseCase(executor, postExecutionThread){

    val logger = LoggerFactory.getLogger(this.javaClass)

    fun execute(username: String, email: String, password: String): Observable<User> {
        logger.debug("execute() called")
        return buildObservable(username, email, password)
                .subscribeOn(Schedulers.from(executor))
                .observeOn(postExecutionThread.scheduler)
    }

    private fun buildObservable(username: String, email: String, password: String): Observable<User> {
        val subject = AsyncSubject<User>()
        usersRepository.create(username, email, password).subscribe({ user ->
            logger.debug("Sign up successful")

            authService.login(username, password).subscribe({ result ->
                logger.debug("Sign in successful")
                subject.onNext(user)
                subject.onCompleted()
            }, { error ->
                logger.error("Sign in failed", error)
                subject.onError(error)
            })
        }, { error ->
            logger.error("Sign up failed", error)
            subject.onError(error)
        })
        return subject
    }

}