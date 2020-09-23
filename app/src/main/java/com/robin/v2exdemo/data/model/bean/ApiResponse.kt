package com.robin.v2exdemo.data.model.bean

import me.hgj.jetpackmvvm.network.BaseResponse

data class ApiResponse<T>(var status: Int, var msg: String, var data: T?) : BaseResponse<T>() {

    override fun isSuccess() = status == 100

    override fun getResponseCode() = status

    override fun getResponseData() = data

    override fun getResponseMsg() = msg

}