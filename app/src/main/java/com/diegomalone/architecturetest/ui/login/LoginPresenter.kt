package br.com.keycar.architecturetest.ui.login

import br.com.keycar.architecturetest.data.LoginDataSource
import br.com.keycar.architecturetest.exception.NoNetworkException
import br.com.keycar.architecturetest.extension.addToCompositeDisposable
import br.com.keycar.architecturetest.ui.SchedulerContract
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import timber.log.Timber

/**
 * Created by Diego Malone on 28/11/17.
 */

class LoginPresenter(private val dataSource: LoginDataSource,
                     private val scheduler: SchedulerContract) : LoginContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    var view: LoginContract.View? = null

    override fun attachView(view: LoginContract.View) {
        this.view = view
    }

    override fun detachView(view: LoginContract.View) {
        this.view = null
    }

    override fun destroy() {
        compositeDisposable.clear()
    }

    override fun attemptLogin(email: String, password: String) {
        view?.let {
            it.showLoadingIndicator(true)

            dataSource.attemptLogin(email, password)
                    .subscribeOn(scheduler.io)
                    .observeOn(scheduler.ui)
                    .doOnError { _ ->
                        it.showLoadingIndicator(false)
                    }
                    .doOnComplete {
                        it.showLoadingIndicator(false)
                    }
                    .subscribe(
                            { user ->
                                Timber.i(user.name)
                            },
                            { e ->
                                val httpException: HttpException? = e as? HttpException

                                if (httpException?.code() == 400) view?.showInvalidCredentialsError()
                                else {
                                    when (e) {
                                        is NoNetworkException -> view?.showNoConnectivityError()
                                        else -> {
                                            Timber.e(e)
                                            view?.showUnknownError()
                                        }
                                    }
                                }
                            }
                    )
                    .addToCompositeDisposable(compositeDisposable)
        }
    }

}