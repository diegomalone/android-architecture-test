package br.com.keycar.architecturetest.di

import br.com.keycar.architecturetest.di.module.ActivityModule
import br.com.keycar.architecturetest.ui.login.LoginActivity
import dagger.Subcomponent

/**
 * Created by Diego Malone on 30/11/17.
 */
@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(loginActivity: LoginActivity)
}