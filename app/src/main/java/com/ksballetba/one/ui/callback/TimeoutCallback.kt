package com.ksballetba.one.ui.callback

import android.content.Context
import android.view.View
import com.kingja.loadsir.callback.Callback
import com.ksballetba.one.R

class TimeoutCallback:Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_timeoutcallback
    }
}