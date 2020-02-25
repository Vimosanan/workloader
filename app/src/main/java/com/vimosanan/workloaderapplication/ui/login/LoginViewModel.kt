package com.vimosanan.workloaderapplication.ui.login

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vimosanan.workloaderapplication.app.Constants
import com.vimosanan.workloaderapplication.network.ApiInterface
import com.vimosanan.workloaderapplication.util.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay

class LoginViewModel(var api: ApiInterface, private var sharedPreferences: SharedPreferences): ViewModel(){

    fun getAccessToken(userName: String, password: String) = liveData(IO) {
        emit(Resource.loading(null, 900))

        try{
            delay(1_000)
            val response = api.getAccessToken(userName, password)

            if(response.isSuccessful){
                response.body()?.let {
                    val accessToken = "token ${it.key}"

                    sharedPreferences.edit().putString(Constants.ACCESS_TOKEN, accessToken).apply()
                    emit(Resource.success(response, response.code()))
                }
            } else {
                emit(Resource.error("Oops something went wrong, Try again!", null, 901))
            }

        } catch (e: Exception){
            emit(Resource.error("Oops something went wrong, Try again!", null, 901))
        }
    }

}