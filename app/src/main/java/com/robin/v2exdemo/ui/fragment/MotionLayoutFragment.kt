package com.robin.v2exdemo.ui.fragment

import android.os.Bundle
import com.robin.v2exdemo.R
import com.robin.v2exdemo.app.base.BaseFragment
import com.robin.v2exdemo.databinding.FragmentMotionlayoutBinding
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

class MotionLayoutFragment : BaseFragment<BaseViewModel, FragmentMotionlayoutBinding>() {
    override fun layoutId(): Int {
        return R.layout.fragment_motionlayout
    }

    override fun initView(savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }
}