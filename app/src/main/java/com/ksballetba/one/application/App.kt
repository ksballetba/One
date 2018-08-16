package com.ksballetba.one.application

import android.app.Application
import com.kingja.loadsir.core.LoadSir
import com.ksballetba.one.ui.callback.TimeoutCallback

class App:Application() {
    override fun onCreate() {
        LoadSir.beginBuilder()
                .addCallback(TimeoutCallback())
                .setDefaultCallback(TimeoutCallback::class.java)
                        .commit()
        super.onCreate()
    }
}