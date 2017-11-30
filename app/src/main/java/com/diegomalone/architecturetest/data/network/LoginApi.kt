package br.com.keycar.architecturetest.data.network

import br.com.keycar.architecturetest.data.entity.UserEntity
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Diego Malone on 28/11/17.
 */
interface LoginApi {

    @POST("login")
    @FormUrlEncoded
    fun attemptLogin(@Field("email") email: String, @Field("password") password: String) : Observable<UserEntity>
}