package com.robin.v2exdemo.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.robin.v2exdemo.R
import com.robin.v2exdemo.app.base.BaseFragment
import com.robin.v2exdemo.app.ext.init
import com.robin.v2exdemo.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*
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
        mainViewpager.init(this, fragments, true)
        mainBottom.init {
            when (it) {
                R.id.menu_hot -> mainViewpager.setCurrentItem(0, false)
                R.id.menu_latest -> mainViewpager.setCurrentItem(1, false)
                R.id.menu_node -> mainViewpager.setCurrentItem(2, false)
            }
        }
        mainViewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> mainBottom.selectedItemId = R.id.menu_hot
                    1 -> mainBottom.selectedItemId = R.id.menu_latest
                    2 -> mainBottom.selectedItemId = R.id.menu_node
                }

            }

        })

    }
}