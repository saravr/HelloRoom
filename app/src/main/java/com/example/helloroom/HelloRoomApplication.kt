package com.example.helloroom

import android.app.Application
import timber.log.Timber
import timber.log.Timber.Forest.plant
import javax.inject.Inject

class HelloRoomApplication: Application() {
    @Inject
    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()

        plant(Timber.DebugTree())

        Timber.v("Room database size: " + AppDatabase.getDatabaseSize(this))
    }

    override fun onTerminate() {
        super.onTerminate()
        database.close()
    }
}
