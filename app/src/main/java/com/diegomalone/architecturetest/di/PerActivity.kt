package br.com.keycar.architecturetest.di

import javax.inject.Scope

/**
 * Created by Diego Malone on 30/11/17.
 *
 * A scoping annotation to permit objects whose lifetime should
 * conform to the life of the Activity to be memorised in the
 * correct component.
 */
@MustBeDocumented
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity