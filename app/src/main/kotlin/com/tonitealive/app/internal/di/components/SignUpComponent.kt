package com.tonitealive.app.internal.di.components

import com.tonitealive.app.internal.di.annotations.PerActivity
import com.tonitealive.app.internal.di.modules.SignUpModule
import com.tonitealive.app.ui.presenters.signup.SignUpPresenter
import com.tonitealive.app.ui.views.signup.SignUpFragment
import dagger.Component

@PerActivity
@Component(modules = arrayOf(SignUpModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface SignUpComponent {
    fun inject(fragment: SignUpFragment)

    fun presenter(): SignUpPresenter
}