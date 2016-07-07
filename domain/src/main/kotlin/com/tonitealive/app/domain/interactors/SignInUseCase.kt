package com.tonitealive.app.domain.interactors

import com.tonitealive.app.domain.repositories.AccountRepository
import rx.Observable
import rx.lang.kotlin.observable


class SignInUseCase(val accountRepository: AccountRepository,
                    val username: String,
                    val password: String) {

    fun execute(): Observable<Boolean> {
        return observable { subscriber ->

        }
    }

}