package com.robin.v2exdemo.ui.fragment

import android.opengl.GLSurfaceView
import android.os.Bundle
import com.robin.v2exdemo.R
import com.robin.v2exdemo.app.base.BaseFragment
import com.robin.v2exdemo.app.ext.initCenterClose
import com.robin.v2exdemo.app.util.OpenGlUtil
import com.robin.v2exdemo.databinding.FragmentAirHockeyBinding
import com.robin.v2exdemo.widget.opengl.AirHockeyRender


import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.nav
import me.hgj.jetpackmvvm.util.Param

class AirHockeyFragment : BaseFragment<BaseViewModel, FragmentAirHockeyBinding>() {
    @Param
    private var glTitle: String = ""
    override fun layoutId(): Int {
        return R.layout.fragment_air_hockey
    }

    override fun initView(savedInstanceState: Bundle?) {
        toolbar.initCenterClose(mActivity, glTitle) {
            nav().navigateUp()
        }
        if (OpenGlUtil.supportsEs2(mActivity)) {
            val airHockeyRender = AirHockeyRender(mActivity)
            mDataBinding.glSurfaceView.setEGLContextClientVersion(2)
            mDataBinding.glSurfaceView.setRenderer(airHockeyRender)
        }

    }

    override fun onResume() {
        super.onResume()
        mDataBinding.glSurfaceView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mDataBinding.glSurfaceView.onPause()
    }
}