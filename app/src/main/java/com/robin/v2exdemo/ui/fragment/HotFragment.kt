package com.robin.v2exdemo.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemLongClickListener
import com.kingja.loadsir.core.LoadService
import com.robin.v2exdemo.R
import com.robin.v2exdemo.app.base.BaseFragment
import com.robin.v2exdemo.app.ext.init
import com.robin.v2exdemo.app.ext.loadListData
import com.robin.v2exdemo.app.ext.loadServiceInit
import com.robin.v2exdemo.app.ext.showMessage
import com.robin.v2exdemo.app.network.stateCallback.ListDataUiState
import com.robin.v2exdemo.data.model.bean.HotBean
import com.robin.v2exdemo.databinding.FragmentHotBinding
import com.robin.v2exdemo.ui.adapter.HotAdapter
import com.robin.v2exdemo.viewmodel.HotViewModel
import com.robin.v2exdemo.widget.recyclerview.SpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_hot.*
import me.hgj.jetpackmvvm.ext.nav
import me.hgj.jetpackmvvm.ext.navigateAction
import me.hgj.jetpackmvvm.ext.parseState

class HotFragment : BaseFragment<HotViewModel, FragmentHotBinding>() {

    private val hotAdapter by lazy { HotAdapter(arrayListOf()) }
    private lateinit var loadsir: LoadService<Any>
    override fun layoutId() = R.layout.fragment_hot

    override fun initView(savedInstanceState: Bundle?) {
        refresh_layout.setOnRefreshListener {
            mViewModel.getHotList()
        }
        loadsir = loadServiceInit(recycler_view) {
            //点击重试时触发的操作
            mViewModel.getHotList()
        }
        recycler_view.init(LinearLayoutManager(mActivity), hotAdapter).let {
            it.addItemDecoration(SpaceItemDecoration(0, ConvertUtils.dp2px(8f), true))
        }
        hotAdapter.setOnItemClickListener { _, view, position ->
            run {
                val hotBean = hotAdapter.data[position]
                nav().navigateAction(R.id.action_to_webFragment, Bundle().apply {
                    putString("url", hotBean.url)
                    putString("webTitle", hotBean.title)
                })
            }
        }
        hotAdapter.setOnItemLongClickListener { adapter, view, position ->
            run {
                when(position){
                    0->{
                        nav().navigateAction(R.id.action_to_gLDemoFragment)
                    }
                }
            }
            true
        }

    }

    override fun lazyLoadData() {
        super.lazyLoadData()
        mViewModel.getHotList()
    }

    override fun createObserver() {
        super.createObserver()
        mViewModel.hotData.observe(viewLifecycleOwner, Observer { resultState ->
            run {
                if (refresh_layout.isRefreshing) {
                    refresh_layout.finishRefresh()
                }
                parseState(
                    resultState,
                    {
                        val listDataUiState = ListDataUiState(
                            isSuccess = true,
                            isRefresh = true,
                            isEmpty = it.isEmpty(),
                            hasMore = false,
                            isFirstEmpty = it.isEmpty(),
                            listData = it
                        )
                        loadListData(listDataUiState, hotAdapter, loadsir, recycler_view)
                    },
                    {
                        showMessage(it.errorMsg)
                    }
                )
            }

        })
    }
}