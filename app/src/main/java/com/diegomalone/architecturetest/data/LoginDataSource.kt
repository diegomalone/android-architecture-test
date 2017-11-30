package br.com.keycar.architecturetest.data

import br.com.keycar.architecturetest.data.entity.UserEntity
import io.reactivex.Observable

/**
 * Created by Diego Malone on 29/11/17.
 */
interface LoginDataSource {

    fun attemptLogin(email: String, password: String): Observable<UserEntity>
}