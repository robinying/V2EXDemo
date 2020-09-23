package com.robin.v2exdemo.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import com.blankj.utilcode.util.VibrateUtils
import com.just.agentweb.AgentWeb
import com.robin.v2exdemo.R
import com.robin.v2exdemo.app.base.BaseFragment
import com.robin.v2exdemo.app.ext.hideSoftKeyboard
import com.robin.v2exdemo.app.ext.initClose
import com.robin.v2exdemo.app.util.CacheUtil
import com.robin.v2exdemo.databinding.FragmentWebBinding
import kotlinx.android.synthetic.main.fragment_web.*
import kotlinx.android.synthetic.main.include_toolbar.*
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.nav
import me.hgj.jetpackmvvm.util.Param
import java.lang.Exception

class WebFragment : BaseFragment<BaseViewModel, FragmentWebBinding>() {
    @Param
    private var url = ""

    @Param
    private var webTitle = ""

    private var mAgentWeb: AgentWeb? = null

    private var preWeb: AgentWeb.PreAgentWeb? = null

    override fun layoutId(): Int {
        return R.layout.fragment_web
    }

    override fun initView(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        toolbar.run {
            //设置menu 关键代码
            mActivity.setSupportActionBar(this)
            initClose(webTitle) {
                hideSoftKeyboard(activity)
                mAgentWeb?.let { web ->
                    if (web.webCreator.webView.canGoBack()) {
                        web.webCreator.webView.goBack()
                    } else {
                        nav().navigateUp()
                    }
                }
            }
        }
        preWeb = AgentWeb.with(this)
            .setAgentWebParent(web_content, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .createAgentWeb()
            .ready()
    }

    override fun lazyLoadData() {
        //加载网页
        mAgentWeb = preWeb?.go(url)
        requireActivity().onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    mAgentWeb?.let { web ->
                        if (web.webCreator.webView.canGoBack()) {
                            web.webCreator.webView.goBack()
                        } else {
                            nav().navigateUp()
                        }
                    }
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.web_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.web_refresh -> {
                //刷新网页
                mAgentWeb?.urlLoader?.reload()
            }
            R.id.web_browser -> {
                //用浏览器打开
                try {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        mAgentWeb?.webLifeCycle?.onPause()
        super.onPause()
    }

    override fun onResume() {
        mAgentWeb?.webLifeCycle?.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        mAgentWeb?.webLifeCycle?.onDestroy()
        mActivity.setSupportActionBar(null)
        super.onDestroy()
    }
}