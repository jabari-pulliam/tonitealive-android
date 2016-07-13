package com.tonitealive.app.domain.interactors

import com.tonitealive.app.domain.executor.PostExecutionThread
import com.tonitealive.app.domain.executor.ThreadExecutor
import com.tonitealive.app.domain.repositories.UsersRepository
import rx.Observable
import rx.lang.kotlin.observable
import rx.schedulers.Schedulers
import javax.inject.Inject


class SignInUseCase
@Inject constructor(executor: ThreadExecutor,
                    postExecutionThread: PostExecutionThread,
                    val usersRepository: UsersRepository)
: UseCase(executor, postExecutionThread) {

    fun execute(username: String, password: String): Observable<Boolean> {
        return buildObservable(username, password)
                .subscribeOn(Schedulers.from(executor))
                .observeOn(postExecutionThread.scheduler)
    }

    fun buildObservable(username: String, password: String): Observable<Boolean> {
        return observable { subscriber ->

        }
    }

}