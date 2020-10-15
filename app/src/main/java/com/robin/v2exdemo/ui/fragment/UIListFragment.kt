package com.robin.v2exdemo.ui.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.robin.v2exdemo.R
import com.robin.v2exdemo.app.base.BaseFragment
import com.robin.v2exdemo.app.ext.init
import com.robin.v2exdemo.app.ext.initCenter
import com.robin.v2exdemo.data.model.bean.UIRouterBean
import com.robin.v2exdemo.databinding.FragmentUiListBinding
import com.robin.v2exdemo.ui.adapter.UIViewAdapter
import com.robin.v2exdemo.widget.recyclerview.SpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_ui_list.*
import kotlinx.android.synthetic.main.include_toolbar.*
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

class UIListFragment : BaseFragment<BaseViewModel, FragmentUiListBinding>() {
    private val uiViewAdapter by lazy { UIViewAdapter(arrayListOf()) }
    private val uiViewList: MutableList<UIRouterBean> = arrayListOf()
    override fun layoutId(): Int {
        return R.layout.fragment_ui_list
    }

    override fun initView(savedInstanceState: Bundle?) {
        toolbar.initCenter(mActivity, "自定义界面")
        recycler_view.init(LinearLayoutManager(mActivity), uiViewAdapter).let {
            it.addItemDecoration(SpaceItemDecoration(0, ConvertUtils.dp2px(8f)))
        }
        uiViewList.add(UIRouterBean("测试一", R.id.action_to_gLOneFragment))
        uiViewList.add(UIRouterBean("测试二", R.id.action_to_gLOneFragment))
        uiViewAdapter.setList(uiViewList)
    }
}