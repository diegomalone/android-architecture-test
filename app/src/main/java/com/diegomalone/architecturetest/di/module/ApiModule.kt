package br.com.keycar.architecturetest.di.module

import android.content.Context
import android.net.ConnectivityManager
import br.com.keycar.architecturetest.BuildConfig
import br.com.keycar.architecturetest.data.network.LoginApi
import br.com.keycar.architecturetest.exception.NoNetworkException
import br.com.keycar.architecturetest.extension.isConnected
import com.diegomalone.architecturetest.AppApplication
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Diego Malone on 30/11/17.
 */
@Module
class ApiModule {

    fun getOkHttpBuilder(): OkHttpClient.Builder {

        // Header interceptor
        val headerInterceptor = Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
                    .addHeader("apiKey", BuildConfig.API_KEY)

            val request = requestBuilder
                    .build()

            chain.proceed(request)
        }

        return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor()
                        .apply { level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE }
                )
                .addInterceptor(headerInterceptor)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(app: AppApplication): OkHttpClient {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return getOkHttpBuilder()
                .addInterceptor { chain ->
                    val requestBuilder = chain.request().newBuilder()

                    if (!connectivityManager.isConnected) {
                        throw NoNetworkException
                    }

                    chain.proceed(requestBuilder.build())
                }
                .build()
    }

    @Provides
    @Singleton
    fun providesLoginpApi(okHttpClient: OkHttpClient, gson: Gson): LoginApi {
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BuildConfig.API_URL)
                .client(okHttpClient)
                .build()

        return retrofit.create(LoginApi::class.java)
    }
}