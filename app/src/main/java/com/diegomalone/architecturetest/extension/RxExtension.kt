package br.com.keycar.architecturetest.extension

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Diego Malone on 30/11/17.
 */
fun Disposable.addToCompositeDisposable(composite: CompositeDisposable) {
    composite.add(this)
}