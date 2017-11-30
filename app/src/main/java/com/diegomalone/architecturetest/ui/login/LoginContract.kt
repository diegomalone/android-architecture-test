package br.com.keycar.architecturetest.ui.login

import br.com.keycar.architecturetest.ui.BasePresenter
import br.com.keycar.architecturetest.ui.BaseView

/**
 * Created by Diego Malone on 28/11/17.
 */
interface LoginContract {
    interface View : BaseView {
        fun showLoadingIndicator(loadingVisible: Boolean)

        fun showNoConnectivityError()
        fun showInvalidCredentialsError()
        fun showUnknownError()
    }

    interface Presenter : BasePresenter<View> {
        fun attemptLogin(email: String, password: String)
    }
}