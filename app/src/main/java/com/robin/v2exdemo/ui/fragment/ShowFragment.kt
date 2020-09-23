package com.robin.v2exdemo.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import com.robin.v2exdemo.R
import com.robin.v2exdemo.app.base.BaseFragment
import com.robin.v2exdemo.app.ext.corner
import com.robin.v2exdemo.app.ext.showMessage
import com.robin.v2exdemo.data.model.bean.NodeInfo
import com.robin.v2exdemo.databinding.FragmentShowBinding
import com.robin.v2exdemo.viewmodel.ShowViewModel
import kotlinx.android.synthetic.main.fragment_show.*
import me.hgj.jetpackmvvm.ext.nav
import me.hgj.jetpackmvvm.ext.navigateAction
import me.hgj.jetpackmvvm.ext.parseState
import me.hgj.jetpackmvvm.ext.util.toHtml

class ShowFragment : BaseFragment<ShowViewModel, FragmentShowBinding>() {


    override fun layoutId(): Int {
        return R.layout.fragment_show
    }

    override fun initView(savedInstanceState: Bundle?) {
        refresh_layout.setOnRefreshListener {
            mViewModel.getShowInfo("python")
            mViewModel.getShowInfo("android")
            mViewModel.getShowInfo("ios")
        }
        tv_python_url.setOnClickListener {
            nav().navigateAction(R.id.action_to_webFragment, Bundle().apply {
                if (mViewModel.pythonNode != null) {
                    putString("webTitle", mViewModel.pythonNode!!.name)
                    putString("url", mViewModel.pythonNode!!.url)
                }
            })
        }
        tv_android_url.setOnClickListener {
            nav().navigateAction(R.id.action_to_webFragment, Bundle().apply {
                if (mViewModel.androidNode != null) {
                    putString("webTitle", mViewModel.androidNode!!.name)
                    putString("url", mViewModel.androidNode!!.url)
                }
            })
        }
        tv_ios_url.setOnClickListener {
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
                if (refresh_layout.isRefreshing) {
                    refresh_layout.finishRefresh()
                }
                parseState(
                    resultState,
                    {
                        when (it.name) {
                            "python" -> {
                                //mDataBinding.pythonNode = it
                                mViewModel.pythonNode = it
                                it.avatar_large?.run {
                                    iv_python.corner(this)
                                }
                                tv_python_header.text = it.header?.toHtml()
                                tv_python_url.text = it.url
                            }
                            "android" -> {
                                //mDataBinding.androidNode = it
                                mViewModel.androidNode = it
                                it.avatar_large?.run {
                                    iv_android.corner(this)
                                }
                                tv_android_header.text = it.header?.toHtml()
                                tv_android_url.text = it.url
                            }
                            "ios" -> {
                                //mDataBinding.iosNode = it
                                mViewModel.iosNode = it
                                it.avatar_large?.run {
                                    iv_ios.corner(this)
                                }
                                tv_ios_header.text = it.header?.toHtml()
                                tv_ios_url.text = it.url
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

}