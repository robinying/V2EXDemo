package com.robin.v2exdemo.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.Observer
import com.robin.v2exdemo.R
import com.robin.v2exdemo.app.base.BaseFragment
import com.robin.v2exdemo.app.ext.corner
import com.robin.v2exdemo.app.ext.init
import com.robin.v2exdemo.app.ext.initCenter
import com.robin.v2exdemo.app.ext.showMessage
import com.robin.v2exdemo.data.model.bean.NodeInfo
import com.robin.v2exdemo.databinding.FragmentShowBinding
import com.robin.v2exdemo.ui.adapter.SpinnerAdapter
import com.robin.v2exdemo.viewmodel.ShowViewModel
import me.hgj.jetpackmvvm.ext.nav
import me.hgj.jetpackmvvm.ext.navigateAction
import me.hgj.jetpackmvvm.ext.parseState
import me.hgj.jetpackmvvm.ext.util.toHtml
import me.hgj.jetpackmvvm.util.LogUtils
import java.lang.Exception

class ShowFragment : BaseFragment<ShowViewModel, FragmentShowBinding>(),
    AdapterView.OnItemSelectedListener {
    // 是否为用户主动操作 Spinner，避免视图重建时恢复状态触发自动跳转
    private var isUserSelecting = false

    override fun layoutId(): Int {
        return R.layout.fragment_show
    }

    override fun initView(savedInstanceState: Bundle?) {
        toolbar.init( "我的")
        mDataBinding.spinner.adapter = SpinnerAdapter(mActivity, resources.getStringArray(R.array.views))
        mDataBinding.spinner.onItemSelectedListener = this
        mDataBinding.spinner.setOnTouchListener { _, _ ->
            isUserSelecting = true
            false
        }
        mDataBinding.refreshLayout.setOnRefreshListener {
            mViewModel.getShowInfo("python")
            mViewModel.getShowInfo("android")
            mViewModel.getShowInfo("ios")
        }
        mDataBinding.tvPythonUrl.setOnClickListener {
            nav().navigateAction(R.id.action_to_webFragment, Bundle().apply {
                if (mViewModel.pythonNode != null) {
                    putString("webTitle", mViewModel.pythonNode!!.name)
                    putString("url", mViewModel.pythonNode!!.url)
                }
            })
        }
        mDataBinding.tvAndroidUrl.setOnClickListener {
            nav().navigateAction(R.id.action_to_webFragment, Bundle().apply {
                if (mViewModel.androidNode != null) {
                    putString("webTitle", mViewModel.androidNode!!.name)
                    putString("url", mViewModel.androidNode!!.url)
                }
            })
        }
        mDataBinding.tvIosUrl.setOnClickListener {
            nav().navigateAction(R.id.action_to_webFragment, Bundle().apply {
                if (mViewModel.iosNode != null) {
                    putString("webTitle", mViewModel.iosNode!!.name)
                    putString("url", mViewModel.iosNode!!.url)
                }
            })
        }

    }

    override fun lazyLoadData() {
        super.lazyLoadData()
        mViewModel.getShowInfo("python")
        mViewModel.getShowInfo("android")
        mViewModel.getShowInfo("ios")
    }

    override fun createObserver() {
        super.createObserver()
        mViewModel.nodeData.observe(viewLifecycleOwner, Observer { resultState ->
            run {
                if (mDataBinding.refreshLayout.isRefreshing) {
                    mDataBinding.refreshLayout.finishRefresh()
                }
                parseState(
                    resultState,
                    {
                        when (it.name) {
                            "python" -> {
                                //mDataBinding.pythonNode = it
                                mViewModel.pythonNode = it
                                it.avatar_large?.run {
                                    mDataBinding.ivPython.corner(this)
                                }
                                mDataBinding.tvPythonHeader.text = it.header?.toHtml()
                                mDataBinding.tvPythonUrl.text = it.url
                            }
                            "android" -> {
                                //mDataBinding.androidNode = it
                                mViewModel.androidNode = it
                                it.avatar_large?.run {
                                    mDataBinding.ivAndroid.corner(this)
                                }
                                mDataBinding.tvAndroidHeader.text = it.header?.toHtml()
                                mDataBinding.tvAndroidUrl.text = it.url
                            }
                            "ios" -> {
                                //mDataBinding.iosNode = it
                                mViewModel.iosNode = it
                                it.avatar_large?.run {
                                    mDataBinding.ivIos.corner(this)
                                }
                                mDataBinding.tvIosHeader.text = it.header?.toHtml()
                                mDataBinding.tvIosUrl.text = it.url
                            }
                        }
                    },
                    {
                        showMessage(it.errorMsg)
                    }
                )
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
    }


    override fun onPause() {
        super.onPause()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        LogUtils.debugInfo("onNothingSelected")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        LogUtils.debugInfo("onItemSelected position:$position userSelect:$isUserSelecting")
        if (!isUserSelecting) {
            return
        }
        isUserSelecting = false
        when (position) {
            0 -> {
                nav().navigateAction(R.id.action_to_uiListFragment)
            }
            1 -> {
                nav().navigateAction(R.id.action_to_gLDemoFragment)
            }
        }

    }

}