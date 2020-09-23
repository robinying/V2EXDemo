package com.robin.v2exdemo.widget.loadCallBack

import com.kingja.loadsir.callback.Callback
import com.robin.v2exdemo.R

class ErrorCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_error
    }

}