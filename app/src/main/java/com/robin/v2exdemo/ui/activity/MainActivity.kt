package com.robin.v2exdemo.ui.activity


import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.blankj.utilcode.util.ToastUtils
import com.robin.v2exdemo.R
import com.robin.v2exdemo.app.base.BaseActivity
import com.robin.v2exdemo.app.util.SettingUtil
import com.robin.v2exdemo.app.util.StatusBarUtil
import com.robin.v2exdemo.databinding.ActivityMainBinding
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.network.manager.NetState

class MainActivity : BaseActivity<BaseViewModel, ActivityMainBinding>() {
    var exitTime = 0L

    override fun layoutId() = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        StatusBarUtil.setColor(this, SettingUtil.getColor(this), 0)
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val nav = Navigation.findNavController(this@MainActivity, R.id.host_fragment)
                if (nav.currentDestination != null && nav.currentDestination!!.id != R.id.mainFragment) {
                    //如果当前界面不是主页，那么直接调用返回即可
                    nav.navigateUp()
                } else {
                    //是主页
                    if (System.currentTimeMillis() - exitTime > 2000) {
                        ToastUtils.showShort("再按一次退出程序")
                        exitTime = System.currentTimeMillis()
                    } else {
                        finish()
                    }
                }
            }
        })
    }

    override fun onNetworkStateChanged(netState: NetState) {
        super.onNetworkStateChanged(netState)
        if (netState.isSuccess) {
            Toast.makeText(applicationContext, "连上网了!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "怎么断网了!", Toast.LENGTH_SHORT).show()
        }
    }
}