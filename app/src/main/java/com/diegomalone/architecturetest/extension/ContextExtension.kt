package br.com.keycar.architecturetest.extension

import android.content.Context
import android.net.ConnectivityManager
import com.diegomalone.architecturetest.AppApplication

/**
 * Created by Diego Malone on 30/11/17.
 */
val ConnectivityManager.isConnected: Boolean
    get() = activeNetworkInfo?.isConnected ?: false

val Context.appComponent
    get() = (applicationContext as AppApplication).appComponent