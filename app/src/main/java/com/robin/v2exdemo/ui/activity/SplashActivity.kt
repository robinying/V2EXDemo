package com.robin.v2exdemo.ui.activity

import android.animation.Animator
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.*
import com.blankj.utilcode.util.AppUtils
import com.robin.v2exdemo.R
import com.robin.v2exdemo.app.base.BaseActivity
import com.robin.v2exdemo.databinding.ActivitySplashBinding
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.runtime.Permission
import kotlinx.android.synthetic.main.activity_splash.*
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

class SplashActivity : BaseActivity<BaseViewModel, ActivitySplashBinding>() {


    override fun layoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initView(savedInstanceState: Bundle?) {
        val pm = this@SplashActivity.packageManager
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
        tv_app_name.startAnimation(animationSet)
        lottie_view.addAnimatorListener(object :
            Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                if (PackageManager.PERMISSION_GRANTED == pm.checkPermission(
                        Permission.WRITE_EXTERNAL_STORAGE,
                        AppUtils.getAppPackageName()
                    )
                ) {
                    selectJump()
                }
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        if (PackageManager.PERMISSION_GRANTED != pm.checkPermission(
                Permission.WRITE_EXTERNAL_STORAGE,
                AppUtils.getAppPackageName()
            )
        ) {
            AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.STORAGE)
                .onGranted { selectJump() }
                .onDenied { finish() }
                .start()
        }
    }

    private fun selectJump() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        finish()
    }
}