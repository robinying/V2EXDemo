package com.robin.v2exdemo.ui.fragment

import android.opengl.GLSurfaceView
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.robin.v2exdemo.R
import com.robin.v2exdemo.app.base.BaseFragment
import com.robin.v2exdemo.app.ext.initCenterClose
import com.robin.v2exdemo.app.util.OpenGlUtil
import com.robin.v2exdemo.databinding.FragmentBasicGraphBinding
import com.robin.v2exdemo.widget.opengl.BasicShapeRender
import com.robin.v2exdemo.widget.opengl.graph.Line
import com.robin.v2exdemo.widget.opengl.graph.Point
import kotlinx.android.synthetic.main.fragment_basic_graph.*
import kotlinx.android.synthetic.main.include_toolbar.*
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.nav
import me.hgj.jetpackmvvm.util.Param

class BasicGraphFragment : BaseFragment<BaseViewModel, FragmentBasicGraphBinding>() {
    @Param
    private var glTitle: String = ""
    lateinit var basicShapeRender: BasicShapeRender

    override fun layoutId(): Int {
        return R.layout.fragment_basic_graph
    }

    override fun initView(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        toolbar.initCenterClose(mActivity, glTitle) {
            nav().navigateUp()
        }
        toolbar.run {
            mActivity.setSupportActionBar(this)
            this.title = ""
        }
        basicShapeRender = BasicShapeRender(mActivity)
        if (OpenGlUtil.supportsEs2(mActivity)) {
            gl_surfaceView.setEGLContextClientVersion(2)
            basicShapeRender.setShape(Line::class.java)
            gl_surfaceView.setRenderer(basicShapeRender)
            gl_surfaceView.renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.basic_graph_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.point -> {
                basicShapeRender.setShape(Point::class.java)
                gl_surfaceView.requestRender()
            }
            R.id.line -> {
                basicShapeRender.setShape(Line::class.java)
                gl_surfaceView.requestRender()
            }
            R.id.circle -> {
                basicShapeRender.setShape(Point::class.java)
            }
            R.id.rectangle -> {
                basicShapeRender.setShape(Point::class.java)
            }
            R.id.polygon -> {
                basicShapeRender.setShape(Point::class.java)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        mActivity.setSupportActionBar(null)
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