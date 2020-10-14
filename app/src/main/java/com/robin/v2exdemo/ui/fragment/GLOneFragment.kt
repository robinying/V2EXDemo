package com.robin.v2exdemo.ui.fragment

import android.content.Context
import android.content.pm.ConfigurationInfo
import android.opengl.GLSurfaceView
import android.os.Build
import android.os.Bundle
import com.hgj.jetpackmvvm.util.activityManager
import com.robin.v2exdemo.R
import com.robin.v2exdemo.app.base.BaseFragment
import com.robin.v2exdemo.app.ext.initCenterClose

import com.robin.v2exdemo.databinding.FragmentGlOneBinding
import com.robin.v2exdemo.widget.opengl.GLOneRender
import kotlinx.android.synthetic.main.fragment_gl_one.*
import kotlinx.android.synthetic.main.include_toolbar.*
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.nav
import me.hgj.jetpackmvvm.util.Param


class GLOneFragment : BaseFragment<BaseViewModel, FragmentGlOneBinding>() {

    @Param
    private var glTitle: String = ""
    override fun layoutId() = R.layout.fragment_gl_one

    override fun initView(savedInstanceState: Bundle?) {
        toolbar.initCenterClose(mActivity, glTitle, R.drawable.ic_back) {
            nav().navigateUp()
        }
        if (supportsEs2(mActivity)) {
            val glOneRender = GLOneRender(mActivity)
            gl_surfaceView.setEGLContextClientVersion(2)
            gl_surfaceView.setRenderer(glOneRender)
            gl_surfaceView.renderMode = GLSurfaceView.RENDERMODE_CONTINUOUSLY
        }
    }

    private fun supportsEs2(context: Context): Boolean {
        val configurationInfo: ConfigurationInfo = context.activityManager.deviceConfigurationInfo
        return (configurationInfo.reqGlEsVersion >= 0x20000
                || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1))
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