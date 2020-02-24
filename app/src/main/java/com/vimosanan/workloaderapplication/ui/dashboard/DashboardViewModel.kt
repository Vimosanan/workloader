package com.vimosanan.workloaderapplication.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vimosanan.workloaderapplication.network.ApiInterface
import com.vimosanan.workloaderapplication.util.Resource
import kotlinx.coroutines.Dispatchers.IO
import org.koin.core.KoinComponent

class DashboardViewModel(private var api: ApiInterface): ViewModel(), KoinComponent {


    fun loadData() = liveData(IO){
        emit(Resource.loading(null, 0))

        //call the api to load user account detail
        try{
            val response = api.getUserDetails("token e945ae028e2355e123cfdf1b4fbb81ad4e5b2ebc")
            emit(Resource.success(response, response.code()))
        }catch (e: Exception){
            emit(Resource.error("Something went wrong!", null, 900))
        }
    }

    fun clockIn() = liveData(IO){
        emit(Resource.loading(null, 0))

        //call the clock in api
        try{
            val response = api.clockIn("token e945ae028e2355e123cfdf1b4fbb81ad4e5b2ebc", -6.2446691, 106.8779625)
            emit(Resource.success(response, response.code()))
        }catch (e: Exception){
            emit(Resource.error("Something went wrong!", null, 900))
        }
    }

    fun clockOut() = liveData(IO){
        emit(Resource.loading(null, 0))

        //call the clock in api
        try{
            val response = api.clockOut("token e945ae028e2355e123cfdf1b4fbb81ad4e5b2ebc", -6.2446691, 106.8779625)
            emit(Resource.success(response, response.code()))
        }catch (e: Exception){
            emit(Resource.error("Something went wrong!", null, 900))
        }
    }
}