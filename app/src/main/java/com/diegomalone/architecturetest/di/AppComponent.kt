package br.com.keycar.architecturetest.di

import br.com.keycar.architecturetest.di.module.ApiModule
import br.com.keycar.architecturetest.di.module.AppModule
import br.com.keycar.architecturetest.di.module.DataModule
import br.com.keycar.architecturetest.di.module.PresenterModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Diego Malone on 30/11/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, DataModule::class, ApiModule::class))
interface AppComponent {
    operator fun plus(presenterModule: PresenterModule): ConfigPersistentComponent
}