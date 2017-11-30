package br.com.keycar.architecturetest.di.module

import br.com.keycar.architecturetest.data.LoginDataSource
import br.com.keycar.architecturetest.di.ConfigPersistent
import br.com.keycar.architecturetest.ui.DefaultScheduler
import br.com.keycar.architecturetest.ui.login.LoginContract
import br.com.keycar.architecturetest.ui.login.LoginPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Diego Malone on 30/11/17.
 */
@Module
class PresenterModule {

    @ConfigPersistent
    @Provides
    fun providesLoginPresenter(dataSource: LoginDataSource): LoginContract.Presenter =
            LoginPresenter(dataSource, DefaultScheduler)
}