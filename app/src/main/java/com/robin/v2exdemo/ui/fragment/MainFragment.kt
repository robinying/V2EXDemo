package com.robin.v2exdemo.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.robin.v2exdemo.R
import com.robin.v2exdemo.app.base.BaseFragment
import com.robin.v2exdemo.app.ext.init
import com.robin.v2exdemo.databinding.FragmentMainBinding
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

class MainFragment : BaseFragment<BaseViewModel, FragmentMainBinding>() {
    private var fragments: ArrayList<Fragment> = arrayListOf()
    private val hotFragment by lazy { HotFragment() }
    private val latestFragment by lazy { LatestFragment() }
    private val showFragment by lazy { ShowFragment() }

    init {
        fragments.add(hotFragment)
        fragments.add(latestFragment)
        fragments.add(showFragment)
    }

    override fun layoutId() = R.layout.fragment_main

    override fun initView(savedInstanceState: Bundle?) {
        mDataBinding.mainViewpager.init(this, fragments, true)
        mDataBinding.mainBottom.init {
            when (it) {
                R.id.menu_hot -> mDataBinding.mainViewpager.setCurrentItem(0, false)
                R.id.menu_latest -> mDataBinding.mainViewpager.setCurrentItem(1, false)
                R.id.menu_node -> mDataBinding.mainViewpager.setCurrentItem(2, false)
            }
        }
        mDataBinding.mainViewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> mDataBinding.mainBottom.selectedItemId = R.id.menu_hot
                    1 -> mDataBinding.mainBottom.selectedItemId = R.id.menu_latest
                    2 -> mDataBinding.mainBottom.selectedItemId = R.id.menu_node
                }

            }

        })

    }
}