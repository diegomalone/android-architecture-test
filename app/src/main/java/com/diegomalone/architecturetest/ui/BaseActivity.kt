package br.com.keycar.architecturetest.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import br.com.keycar.architecturetest.di.ActivityComponent
import br.com.keycar.architecturetest.di.module.ActivityModule

/**
 * Created by Diego Malone on 28/11/17.
 */
open class BaseActivity : AppCompatActivity() {

    val configPersistDelegate = ConfigPersistentDelegate()

    lateinit var activityComponent: ActivityComponent

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        configPersistDelegate.onSaveInstanceState(outState ?: return)
    }

    override fun onDestroy() {
        super.onDestroy()
        configPersistDelegate.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        configPersistDelegate.onCreate(this, savedInstanceState)
        activityComponent = configPersistDelegate.component + ActivityModule(this)

        super.onCreate(savedInstanceState)
    }

    fun showSnack(parent: ViewGroup, messageResId: Int, length: Int,
                  actionLabelResId: Int? = null, action: ((View) -> Unit)? = null,
                  callback: ((Snackbar) -> Unit)? = null) {

        showSnack(parent, getString(messageResId), length, actionLabelResId?.let { getString(it) }, action, callback)
    }

    private fun showSnack(parent: ViewGroup, message: String, length: Int,
                          actionLabel: String? = null, action: ((View) -> Unit)? = null,
                          callback: ((Snackbar) -> Unit)? = null) {

        Snackbar.make(parent, message, length)
                .apply {
                    if (actionLabel != null) {
                        setAction(actionLabel, action)
                    }

                    callback?.invoke(this)
                }
                .show()
    }
}