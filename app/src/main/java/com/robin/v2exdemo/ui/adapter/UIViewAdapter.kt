package com.robin.v2exdemo.ui.adapter

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.robin.v2exdemo.R
import com.robin.v2exdemo.data.model.bean.UIRouterBean
import com.robin.v2exdemo.databinding.ItemUiViewBinding

class UIViewAdapter(data: ArrayList<UIRouterBean>) :
    BaseQuickAdapter<UIRouterBean, BaseViewHolder>(R.layout.item_ui_view) {
    override fun convert(holder: BaseViewHolder, item: UIRouterBean) {
        val itemUiViewBinding =
            DataBindingUtil.getBinding<ItemUiViewBinding>(holder.itemView)
        if (itemUiViewBinding != null) {
            itemUiViewBinding.uiRouterBean = item
            itemUiViewBinding.executePendingBindings()
        }
    }

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        super.onItemViewHolderCreated(viewHolder, viewType)
        DataBindingUtil.bind<ItemUiViewBinding>(viewHolder.itemView)
    }
}