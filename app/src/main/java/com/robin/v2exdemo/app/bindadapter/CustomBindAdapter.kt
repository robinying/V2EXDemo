package com.robin.v2exdemo.app.bindadapter

import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.widget.*
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.robin.v2exdemo.widget.glide.CornerTransform
import me.hgj.jetpackmvvm.ext.util.dp2px
import me.hgj.jetpackmvvm.ext.view.textString



/**
 * 作者　: hegaojian
 * 时间　: 2019/12/23
 * 描述　: 自定义 BindingAdapter
 */
object CustomBindAdapter {

    @BindingAdapter(value = ["checkChange"])
    @JvmStatic
    fun checkChange(checkbox: CheckBox, listener: CompoundButton.OnCheckedChangeListener) {
        checkbox.setOnCheckedChangeListener(listener)
    }

    @BindingAdapter(value = ["switchChange"])
    @JvmStatic
    fun switchChange(switch: Switch, listener: CompoundButton.OnCheckedChangeListener) {
        switch.setOnCheckedChangeListener(listener)
    }

    @BindingAdapter(value = ["showPwd"])
    @JvmStatic
    fun showPwd(view: EditText, boolean: Boolean) {
        if (boolean) {
            view.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            view.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        view.setSelection(view.textString().length)
    }

    @BindingAdapter(value = ["imageUrl"])
    @JvmStatic
    fun imageUrl(view: ImageView, url: String) {
        Glide.with(view.context.applicationContext)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(view)
    }

    @BindingAdapter(value = ["cornerImageUrl"],requireAll = false)
    @JvmStatic
    fun cornerImageUrl(view: ImageView, url: String) {
        val transformation = CornerTransform(
            view.context.applicationContext,
            view.context.applicationContext.dp2px(6).toFloat()
        )
        Glide.with(view.context.applicationContext)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .transform(transformation)
            .into(view)
    }

    @BindingAdapter(value = ["circleImageUrl"])
    @JvmStatic
    fun circleImageUrl(view: ImageView, url: String) {
        Glide.with(view.context.applicationContext)
            .load(url)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(view)
    }



    @BindingAdapter(value = ["afterTextChanged"])
    @JvmStatic
    fun EditText.afterTextChanged(action: (String) -> Unit) {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                action(s.toString())
            }
        })
    }

}