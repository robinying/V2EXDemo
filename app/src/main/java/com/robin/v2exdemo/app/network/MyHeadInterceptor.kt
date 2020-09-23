package com.robin.v2exdemo.app.network

import com.robin.v2exdemo.app.util.CacheUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * 自定义头部参数拦截器，传入heads
 */
class MyHeadInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        if(!CacheUtil.getToken().isNullOrEmpty()){
            builder.addHeader("Authorization",CacheUtil.getToken())
        }
        val response = chain.proceed(builder.build())
        val token = response.header("Authorization")
        if(!token.isNullOrEmpty()){
            CacheUtil.setToken(token)
        }
        return response
    }

}