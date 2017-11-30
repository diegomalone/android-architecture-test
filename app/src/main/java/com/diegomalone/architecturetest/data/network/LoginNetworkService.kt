package br.com.keycar.architecturetest.data.network

import br.com.keycar.architecturetest.data.LoginDataSource
import br.com.keycar.architecturetest.data.entity.UserEntity
import io.reactivex.Observable

/**
 * Created by Diego Malone on 29/11/17.
 */
class LoginNetworkService(private val api: LoginApi) : LoginDataSource {

    override fun attemptLogin(email: String, password: String): Observable<UserEntity> {
        return api.attemptLogin(email, password)
    }
}