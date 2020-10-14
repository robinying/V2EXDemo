package com.robin.v2exdemo.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.robin.v2exdemo.R
import com.robin.v2exdemo.app.ext.setAdapterAnimation
import com.robin.v2exdemo.app.util.SettingUtil
import com.robin.v2exdemo.data.model.bean.RenderBean


class RenderAdapter(data: ArrayList<RenderBean>) :
    BaseQuickAdapter<RenderBean, BaseViewHolder>(R.layout.item_render) {
    init {
        setAdapterAnimation(SettingUtil.getListMode())
    }

    override fun convert(holder: BaseViewHolder, item: RenderBean) {
        item.run {
            holder.setText(R.id.tv_render_title, mTitle)
        }
    }
}