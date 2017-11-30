package br.com.keycar.architecturetest.ui.login

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import br.com.keycar.architecturetest.R
import br.com.keycar.architecturetest.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

/**
 * Created by Diego Malone on 28/11/17.
 */
class LoginActivity : BaseActivity(), LoginContract.View {

    @Inject
    lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        activityComponent.inject(this)

        presenter.attachView(this)

        configureUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView(this)

        if (!configPersistDelegate.instanceSaved) {
            presenter.destroy()
        }
    }

    override fun showLoadingIndicator(loadingVisible: Boolean) {
        progressBar.visibility = if (loadingVisible) View.VISIBLE else View.GONE
    }

    override fun showNoConnectivityError() {
        showSnack(constraintLayout, R.string.error_no_connectivity, Snackbar.LENGTH_LONG)
    }

    override fun showInvalidCredentialsError() {
        showSnack(constraintLayout, R.string.error_invalid_credentials, Snackbar.LENGTH_LONG)
    }

    override fun showUnknownError() {
        showSnack(constraintLayout, R.string.error_unexpected, Snackbar.LENGTH_LONG)
    }

    private fun configureUI() {
        loginButton.setOnClickListener {
            presenter.attemptLogin(emailEditText.text.toString(), passwordEditText.text.toString())
        }
    }
}
