package br.com.keycar.architecturetest.di.module

import android.content.Context
import com.diegomalone.architecturetest.AppApplication
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Diego Malone on 30/11/17.
 */
@Module
class AppModule(private val application: AppApplication) {

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = application

    @Provides
    @Singleton
    fun providesApplication(): AppApplication = application

    @Provides
    @Singleton
    fun providesGson(): Gson =
            GsonBuilder()
                    .serializeNulls()
                    .create()
}