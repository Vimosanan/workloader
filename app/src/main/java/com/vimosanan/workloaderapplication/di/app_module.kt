package com.vimosanan.workloaderapplication.di

import com.vimosanan.workloaderapplication.app.Constants
import com.vimosanan.workloaderapplication.network.ApiInterface
import com.vimosanan.workloaderapplication.ui.dashboard.DashboardViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var appModule =  module {

    viewModel {
        DashboardViewModel(
            get(
                named("apiInterface")
            )
        )
    }

    factory(named("apiInterface")) {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

}