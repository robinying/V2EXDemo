package com.robin.v2exdemo.data.repository.request

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

import me.hgj.jetpackmvvm.network.AppException

/**
 * 作者　: hegaojian
 * 时间　: 2020/5/4
 * 描述　: 处理协程的请求类
 */

val HttpRequestCoroutine: HttpRequestManger by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    HttpRequestManger()
}

class HttpRequestManger {


}