package br.com.keycar.architecturetest.ui

/**
 * Created by Diego Malone on 28/11/17.
 */
interface BasePresenter<in V : BaseView> {
    fun detachView(view: V)
    fun attachView(view: V)

    fun destroy()
}