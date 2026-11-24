package com.robin.v2exdemo.app.base

import android.content.res.Resources
import android.os.Bundle
import android.os.Looper
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.ViewDataBinding
import com.robin.v2exdemo.R
import com.robin.v2exdemo.app.event.AppViewModel
import com.robin.v2exdemo.app.event.EventViewModel
import com.robin.v2exdemo.app.ext.dismissLoadingExt
import com.robin.v2exdemo.app.ext.showLoadingExt
import me.hgj.jetpackmvvm.base.activity.BaseVmDbActivity
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.getAppViewModel
import me.jessyan.autosize.AutoSizeCompat

/**
 * 描述　: 你项目中的Activity基类，在这里实现显示弹窗，吐司，还有加入自己的需求操作 ，如果不想用Databind，请继承
 * BaseVmActivity例如
 * abstract class BaseActivity<VM : BaseViewModel> : BaseVmActivity<VM>() {
 */
abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmDbActivity<VM, DB>() {

    //Application全局的ViewModel，里面存放了一些账户信息，基本配置信息等
    val appViewModel: AppViewModel by lazy { getAppViewModel<AppViewModel>()}

    //Application全局的ViewModel，用于发送全局通知操作
    val eventViewModel: EventViewModel by lazy { getAppViewModel<EventViewModel>() }

    /** 通用标题栏，对应 include_toolbar.xml 中的 id */
    protected val toolbar: Toolbar
        get() = mDataBinding.root.findViewById(R.id.toolbar)

    /** targetSdk 36 起强制 edge-to-edge，默认给根布局补系统栏内边距，全屏页可重写为 false */
    protected open val applySystemBarInsets: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyEdgeToEdge()
    }

    /**
     * 适配 Android 15+ 强制 edge-to-edge：内容延伸到系统栏下方，再由根布局的 padding 让出空间
     */
    private fun applyEdgeToEdge() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, mDataBinding.root).isAppearanceLightStatusBars = true
        if (!applySystemBarInsets) {
            return
        }
        ViewCompat.setOnApplyWindowInsetsListener(mDataBinding.root) { view, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )
            view.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }
    }

    abstract override fun layoutId(): Int

    abstract override fun initView(savedInstanceState: Bundle?)

    /**
     * 创建liveData观察者
     */
    override fun createObserver() {}

    /**
     * 打开等待框
     */
    override fun showLoading(message: String) {
        showLoadingExt(message)
    }

    /**
     * 关闭等待框
     */
    override fun dismissLoading() {
        dismissLoadingExt()
    }

    /**
     * 在任何情况下本来适配正常的布局突然出现适配失效，适配异常等问题，只要重写 Activity 的 getResources() 方法
     */
    override fun getResources(): Resources {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return super.getResources()
        }
        AutoSizeCompat.autoConvertDensityOfGlobal(super.getResources())
        return super.getResources()
    }
}