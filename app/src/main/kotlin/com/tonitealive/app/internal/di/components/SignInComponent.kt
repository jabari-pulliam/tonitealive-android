package com.tonitealive.app.internal.di.components

import com.tonitealive.app.internal.di.annotations.PerFragment
import com.tonitealive.app.internal.di.modules.SignInModule
import com.tonitealive.app.ui.presenters.signin.SignInPresenter
import com.tonitealive.app.ui.views.signin.SignInFragment
import com.tonitealive.app.ui.views.signin.SignInView
import dagger.Component

@PerFragment
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(SignInModule::class))
interface SignInComponent {
    fun inject(fragment: SignInFragment)

    fun presenter(): SignInPresenter
    fun view(): SignInView
}