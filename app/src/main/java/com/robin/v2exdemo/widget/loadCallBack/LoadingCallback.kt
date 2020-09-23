package com.robin.v2exdemo.widget.loadCallBack

import android.content.Context
import android.view.View
import com.kingja.loadsir.callback.Callback
import com.robin.v2exdemo.R

class LoadingCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_loading
    }

    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        return true
    }
}