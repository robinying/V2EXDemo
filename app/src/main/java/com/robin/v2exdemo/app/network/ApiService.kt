package com.robin.v2exdemo.app.network

import com.robin.v2exdemo.app.util.ConstantUtil
import com.robin.v2exdemo.data.model.bean.HotBean
import com.robin.v2exdemo.data.model.bean.NodeInfo
import com.tencent.bugly.crashreport.biz.UserInfoBean
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * 描述　: 网络API
 */
interface ApiService {

    companion object {
        const val SERVER_URL = ConstantUtil.BASE_URL
    }

    @GET("topics/hot.json")
    suspend fun getHotList(): ArrayList<HotBean>

    @GET("topics/latest.json")
    suspend fun getLatestList(): ArrayList<HotBean>

    @GET("nodes/show.json")
    suspend fun getNodeInfo(@Query("name") name: String):NodeInfo

}