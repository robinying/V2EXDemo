package com.robin.v2exdemo.data.model.bean

import androidx.annotation.IdRes

data class UIRouterBean(
    var title: String,
    @IdRes
    var id: Int)