package com.vimosanan.workloaderapplication.network

import com.vimosanan.workloaderapplication.model.Account
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {

    /**
     * @param userName
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST
    fun getAccessToker(
            @Field("username") userName: String,
            @Field("password") password: String): Response<String>

    /**
     * @param accessToken
     * @return
     */
    fun getUserDetails(
            @Header("Authorization") accessToken: String): Response<Account>
}