package br.com.keycar.architecturetest.di.module

import br.com.keycar.architecturetest.data.LoginDataSource
import br.com.keycar.architecturetest.data.network.LoginApi
import br.com.keycar.architecturetest.data.network.LoginNetworkService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Diego Malone on 30/11/17.
 */

@Module
class DataModule {

    @Singleton
    @Provides
    fun providesLoginDataSource(api: LoginApi): LoginDataSource = LoginNetworkService(api)
}