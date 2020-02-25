package com.vimosanan.workloaderapplication.network

import com.vimosanan.workloaderapplication.model.AccessToken
import com.vimosanan.workloaderapplication.model.Account
import com.vimosanan.workloaderapplication.model.ClockInOutObj
import com.vimosanan.workloaderapplication.model.TimeSheet
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    /**
     * @param userName
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("v1/auth/login/")
    suspend fun getAccessToken(
            @Field("username") userName: String,
            @Field("password") password: String): Response<AccessToken>

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
            @Field("longitude") longitude: Double): Response<ClockInOutObj>


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
            @Field("longitude") longitude: Double): Response<TimeSheet>
}