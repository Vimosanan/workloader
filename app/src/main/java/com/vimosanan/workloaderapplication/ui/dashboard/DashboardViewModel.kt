package com.vimosanan.workloaderapplication.ui.dashboard

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vimosanan.workloaderapplication.app.Constants
import com.vimosanan.workloaderapplication.network.ApiInterface
import com.vimosanan.workloaderapplication.util.CommonUtil
import com.vimosanan.workloaderapplication.util.Resource
import kotlinx.coroutines.Dispatchers.IO
import org.koin.core.KoinComponent
import java.text.SimpleDateFormat
import java.util.*

class DashboardViewModel(private var api: ApiInterface, private var sharedPreferences: SharedPreferences): ViewModel(), KoinComponent {

    val clockInTime = MutableLiveData<String> ()
    val clockOutTime = MutableLiveData<String> ()


    fun loadData() = liveData(IO){
        emit(Resource.loading(null, 0))

        //call the api to load user account detail
        try{
            val accessToken = sharedPreferences.getString(Constants.ACCESS_TOKEN, "")
            if(accessToken != null && accessToken != ""){
                val response = api.getUserDetails(accessToken)
                emit(Resource.success(response, response.code()))
            }
        }catch (e: Exception){
            emit(Resource.error("Something went wrong!", null, 900))
        }
    }

    fun clockIn() = liveData(IO){
        emit(Resource.loading(null, 0))

        //call the clock in api
        try{
            val accessToken = sharedPreferences.getString(Constants.ACCESS_TOKEN, "")
            if(accessToken != null && accessToken != ""){
                val response = api.clockIn(accessToken, -6.2446691, 106.8779625)

                if(response.isSuccessful){
                    response.body()?.let{
                        val str =  getDisplayDateString(it.clockInTime!!)
                        clockInTime.postValue(str)
                    }
                }
                emit(Resource.success(response, response.code()))
            }
        }catch (e: Exception){
            emit(Resource.error("Something went wrong!", null, 900))
        }
    }

    fun clockOut() = liveData(IO){
        emit(Resource.loading(null, 0))

        //call the clock out api
        try{
            val accessToken = sharedPreferences.getString(Constants.ACCESS_TOKEN, "")
            if(accessToken != null && accessToken != ""){
                val response = api.clockOut(accessToken, -6.2446691, 106.8779625)

                if(response.isSuccessful){
                    response.body()?.let{
                        val str = it.clockInOut?.clockOutTime?.let { it1 -> getDisplayDateString(it1) }
                        str?.let {
                            clockOutTime.postValue(str)
                        }
                    }
                }
                emit(Resource.success(response, response.code()))
            }
        }catch (e: Exception){
            emit(Resource.error("Something went wrong!", null, 900))
        }
    }

    private fun getDisplayDateString(str: String): String{
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        format.timeZone = TimeZone.getTimeZone("UTC")
        val dateObj = format.parse(str)!!

        val list = CommonUtil.dateToString(dateObj).split(" ")

        return "${list[1]}${list[2]}"
    }
}