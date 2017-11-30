package com.diegomalone.architecturetest

import android.app.Application
import br.com.keycar.architecturetest.BuildConfig
import br.com.keycar.architecturetest.di.AppComponent
import br.com.keycar.architecturetest.di.DaggerAppComponent
import br.com.keycar.architecturetest.di.module.AppModule
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber

/**
 * Created by Diego Malone on 30/11/17.
 */
class AppApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        buildComponent()
        configureExceptionLogging()
    }

    fun configureExceptionLogging() {
        val default = Thread.getDefaultUncaughtExceptionHandler()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        Thread.setDefaultUncaughtExceptionHandler { thread, e ->
            Timber.e(e)
            default.uncaughtException(thread, e)
        }

        RxJavaPlugins.setErrorHandler(Timber::e)
    }

    fun buildComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}