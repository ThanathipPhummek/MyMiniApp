package com.adedom.myminiapp

import android.app.Application
import com.adedom.core.coreModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(
                appModule,
                coreModule,
            )
        }
    }
}
