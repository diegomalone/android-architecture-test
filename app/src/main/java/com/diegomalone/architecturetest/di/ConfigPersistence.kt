package br.com.keycar.architecturetest.di

import javax.inject.Scope

/**
 * Created by Diego Malone on 30/11/17.
 *
 * A scoping annotation to permit dependencies conform to the life of the
 * [ConfigPersistentComponent]
 */
@MustBeDocumented
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ConfigPersistent