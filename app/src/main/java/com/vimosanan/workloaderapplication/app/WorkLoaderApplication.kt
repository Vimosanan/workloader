package com.vimosanan.workloaderapplication.app

import android.app.Application
import com.vimosanan.workloaderapplication.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WorkLoaderApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        //start Koin Context
        startKoin {
            androidLogger()
            androidContext(this@WorkLoaderApplication)
            modules(appModule)
        }
    }

}