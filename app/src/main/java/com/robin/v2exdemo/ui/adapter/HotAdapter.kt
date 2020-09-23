package com.robin.v2exdemo.ui.adapter

import androidx.databinding.DataBindingUtil
import com.blankj.utilcode.constant.TimeConstants
import com.blankj.utilcode.util.SpanUtils
import com.blankj.utilcode.util.TimeUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.robin.v2exdemo.R
import com.robin.v2exdemo.app.ext.setAdapterAnimation
import com.robin.v2exdemo.app.util.DatetimeUtil
import com.robin.v2exdemo.app.util.SettingUtil
import com.robin.v2exdemo.data.model.bean.HotBean
import com.robin.v2exdemo.databinding.ItemHotBinding
import me.hgj.jetpackmvvm.util.LogUtils

class HotAdapter(data: ArrayList<HotBean>) :
    BaseQuickAdapter<HotBean, BaseViewHolder>(R.layout.item_hot) {

    init {
        setAdapterAnimation(SettingUtil.getListMode())
    }

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<ItemHotBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: HotBean) {
        item.run {
            val itemHotBinding =
                DataBindingUtil.getBinding<ItemHotBinding>(holder.itemView)
            if (itemHotBinding != null) {
                itemHotBinding.hotBean = this
                itemHotBinding.executePendingBindings()
                if (last_modified != null) {
                    itemHotBinding.tvLastModifyTime.text =
                        DatetimeUtil.formatDate(last_modified * 1000, DatetimeUtil.DATE_PATTERN_MM)
                }
                if (!last_reply_by.isNullOrEmpty()) {
                    SpanUtils.with(itemHotBinding.tvLastReply)
                        .append("最后回复来自")
                        .setForegroundColor(context.resources.getColor(R.color.gray))
                        .append(last_reply_by)
                        .setForegroundColor(context.resources.getColor(R.color.colorBlack333))
                        .setBold()
                        .create()
                }

            }

        }
    }
}