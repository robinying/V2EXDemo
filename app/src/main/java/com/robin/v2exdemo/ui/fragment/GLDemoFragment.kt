package com.robin.v2exdemo.ui.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.robin.v2exdemo.R
import com.robin.v2exdemo.app.base.BaseFragment
import com.robin.v2exdemo.app.ext.init

import com.robin.v2exdemo.app.ext.initCenter
import com.robin.v2exdemo.data.model.bean.RenderBean
import com.robin.v2exdemo.databinding.FragmentGlDemoBinding
import com.robin.v2exdemo.ui.adapter.RenderAdapter
import com.robin.v2exdemo.widget.recyclerview.SpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_gl_demo.*
import kotlinx.android.synthetic.main.include_toolbar.*
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.nav

class GLDemoFragment : BaseFragment<BaseViewModel, FragmentGlDemoBinding>() {
    private val renderAdapter by lazy { RenderAdapter(arrayListOf()) }
    private val renderDataList: MutableList<RenderBean> = arrayListOf()

    override fun layoutId() = R.layout.fragment_gl_demo

    override fun initView(savedInstanceState: Bundle?) {
        toolbar.initCenter(mActivity, "OpenGLDemo")
        recycler_view.init(LinearLayoutManager(mActivity), renderAdapter).let {
            it.addItemDecoration(SpaceItemDecoration(0, ConvertUtils.dp2px(8f), true))
        }
        renderAdapter.setOnItemClickListener { adapter, view, position ->
            run {
                val renderModel = renderAdapter.data[position]
                nav().navigate(renderModel.mType, Bundle().apply {
                    putString("glTitle", renderModel.mTitle)
                })
            }
        }
        renderDataList.add(
            RenderBean(
                "基本使用",
                R.id.action_to_gLOneFragment,
                GLOneFragment::class.java
            )
        )
        renderDataList.add(
            RenderBean(
                "空气曲棍球绘制",
                R.id.action_to_airHockeyFragment,
                AirHockeyFragment::class.java
            )
        )
        renderDataList.add(
            RenderBean("基本图形绘制", R.id.action_to_basicGraphFragment, BasicGraphFragment::class.java)
        )
        renderAdapter.setList(renderDataList)
    }

}