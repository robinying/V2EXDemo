package com.robin.v2exdemo.ui.activity

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cat.ereza.customactivityoncrash.CustomActivityOnCrash
import com.robin.v2exdemo.BuildConfig
import com.robin.v2exdemo.R
import com.robin.v2exdemo.app.base.BaseActivity
import com.robin.v2exdemo.app.ext.init
import com.robin.v2exdemo.app.util.SettingUtil
import com.robin.v2exdemo.app.util.StatusBarUtil
import com.robin.v2exdemo.databinding.ActivityErrorBinding
import kotlinx.android.synthetic.main.activity_error.*
import kotlinx.android.synthetic.main.include_toolbar.*
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.view.clickNoRepeat

class ErrorActivity : BaseActivity<BaseViewModel, ActivityErrorBinding>() {


    override fun layoutId(): Int {
        return R.layout.activity_error
    }

    override fun initView(savedInstanceState: Bundle?) {
        toolbar.init("发生错误")
        supportActionBar?.setBackgroundDrawable(ColorDrawable(SettingUtil.getColor(this)))
        StatusBarUtil.setColor(this, SettingUtil.getColor(this), 0)
        val config = CustomActivityOnCrash.getConfigFromIntent(intent)
        errorRestart.clickNoRepeat {
            config?.run {
                CustomActivityOnCrash.restartApplication(this@ErrorActivity, this)
            }
        }
        CustomActivityOnCrash.getStackTraceFromIntent(intent)?.let {
            if (BuildConfig.DEBUG) {
                tv_error_info.text = it
            }
        }

    }
}