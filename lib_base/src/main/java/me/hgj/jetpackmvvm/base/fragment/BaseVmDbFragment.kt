package me.hgj.jetpackmvvm.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/12
 * 描述　: ViewModelFragment基类，自动把ViewModel注入Fragment和Databind注入进来了
 * 需要使用Databind的清继承它
 */
abstract class BaseVmDbFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmFragment<VM>() {

    private var _binding: DB? = null

    //该类绑定的ViewDataBinding，仅在 onCreateView 到 onDestroyView 之间有效
    val mDataBinding: DB
        get() = _binding
            ?: throw IllegalStateException("${javaClass.simpleName} 的 mDataBinding 只能在 onCreateView 与 onDestroyView 之间访问")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //绑定到 viewLifecycleOwner：视图销毁后 LiveData 不再驱动视图
        _binding?.lifecycleOwner = viewLifecycleOwner
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        //视图销毁时解绑并释放引用，避免 Fragment 进入回退栈后泄漏整棵视图树
        _binding?.unbind()
        _binding = null
        super.onDestroyView()
    }

}
