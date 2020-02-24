package com.vimosanan.workloaderapplication.network

import com.vimosanan.workloaderapplication.model.Account
import retrofit2.Response
import retrofit2.http.*
import java.util.*

interface ApiInterface {

    /**
     * @param userName
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("/")
    fun getAccessToker(
            @Field("username") userName: String,
            @Field("password") password: String): Response<String>

    /**
     * @param accessToken
     * @return
     */
    @GET("v1/staff-requests/26074/")
    suspend fun getUserDetails(
            @Header("Authorization") accessToken: String): Response<Account>


    /**
     * @param accessToken
     * @param latitude
     * @param longitude
     * @return
     */
    @FormUrlEncoded
    @POST("v1/staff-requests/26074/clock-in/")
    suspend fun clockIn(
            @Header("Authorization") accessToken: String,
            @Field("latitude") latitude: Double,
            @Field("longitude") longitude: Double): Response<Object>


    /**
     * @param accessToken
     * @param latitude
     * @param longitude
     * @return
     */
    @FormUrlEncoded
    @POST("v1/staff-requests/26074/clock-out/")
    suspend fun clockOut(
            @Header("Authorization") accessToken: String,
            @Field("latitude") latitude: Double,
            @Field("longitude") longitude: Double): Response<Object>
}