package br.com.keycar.architecturetest.flow

import android.content.Context
import android.content.Intent
import br.com.keycar.architecturetest.ui.login.LoginActivity

/**
 * Created by Diego Malone on 28/11/17.
 */
class FlowController {

    fun launchLoginActivity(context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
    }
}