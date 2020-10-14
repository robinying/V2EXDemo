package com.robin.v2exdemo.ui.fragment

import android.opengl.GLSurfaceView
import android.os.Bundle
import com.robin.v2exdemo.R
import com.robin.v2exdemo.app.base.BaseFragment
import com.robin.v2exdemo.app.ext.initCenterClose
import com.robin.v2exdemo.app.util.OpenGlUtil
import com.robin.v2exdemo.databinding.FragmentAirHockeyBinding
import com.robin.v2exdemo.widget.opengl.AirHockeyRender
import kotlinx.android.synthetic.main.fragment_air_hockey.*
import kotlinx.android.synthetic.main.include_toolbar.*


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
            gl_surfaceView.setEGLContextClientVersion(2)
            gl_surfaceView.setRenderer(airHockeyRender)
        }

    }

    override fun onResume() {
        super.onResume()
        gl_surfaceView.onResume()
    }

    override fun onPause() {
        super.onPause()
        gl_surfaceView.onPause()
    }
}