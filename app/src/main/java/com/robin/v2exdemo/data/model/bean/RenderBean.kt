package com.robin.v2exdemo.data.model.bean

import androidx.annotation.IdRes

class RenderBean {
    var mTitle: String
    @IdRes
    var mType: Int
    var mClass: Class<*>? = null

    constructor(mTitle: String, mActionId: Int) {
        this.mTitle = mTitle
        this.mType = mActionId
    }

    constructor(title: String, mActionId: Int, aClass: Class<*>?) {
        mTitle = title
        mType = mActionId
        mClass = aClass
    }
}