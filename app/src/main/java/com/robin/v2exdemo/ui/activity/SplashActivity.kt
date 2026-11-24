package com.robin.v2exdemo.ui.activity

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import com.robin.v2exdemo.R
import com.robin.v2exdemo.app.base.BaseActivity
import com.robin.v2exdemo.databinding.ActivitySplashBinding
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

class SplashActivity : BaseActivity<BaseViewModel, ActivitySplashBinding>() {

    // 启动页为全屏视觉，内容延伸至状态栏下方
    override val applySystemBarInsets: Boolean = false

    override fun layoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initView(savedInstanceState: Bundle?) {
        val scaleAnimation =
            ScaleAnimation(
                0.5f,
                1.5f,
                0.5f,
                1.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
        scaleAnimation.duration = 1000
        scaleAnimation.interpolator = AccelerateDecelerateInterpolator()
        val rotateAnimation =
            RotateAnimation(
                0F, 360F, RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f
            )
        rotateAnimation.duration = 1000
        rotateAnimation.interpolator = AccelerateDecelerateInterpolator()
        val animationSet =
            AnimationSet(true)
        animationSet.addAnimation(scaleAnimation)
        animationSet.addAnimation(rotateAnimation)
        animationSet.fillAfter = true
        mDataBinding.tvAppName.startAnimation(animationSet)
        mDataBinding.lottieView.addAnimatorListener(object :
            Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                // 存储权限门禁已移除：targetSdk 33+ 上 WRITE_EXTERNAL_STORAGE 恒为拒绝，
                // 保留原逻辑会导致启动后立即 finish()
                selectJump()
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
    }

    private fun selectJump() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        finish()
    }
}
