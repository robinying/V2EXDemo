package com.robin.v2exdemo.app.util

import com.tencent.mmkv.MMKV

object CacheUtil {

    /**
     * 是否已经登录
     */
    fun isLogin(): Boolean {
        val kv = MMKV.mmkvWithID("app")
        return kv.decodeBool("login", false)
    }

    /**
     * 设置是否已经登录
     */
    fun setIsLogin(isLogin: Boolean) {
        val kv = MMKV.mmkvWithID("app")
        kv.encode("login", isLogin)
    }

    fun isStoreInfo(): Boolean {
        val kv = MMKV.mmkvWithID("app")
        return kv.decodeBool("store_info", false)
    }

    /**
     * 设置是否已经登录
     */
    fun setStoreInfo(isChecked: Boolean) {
        val kv = MMKV.mmkvWithID("app")
        kv.encode("store_info", isChecked)
    }


    fun setToken(token:String?){
        val kv = MMKV.mmkvWithID("app")
        if (token == null) {
            kv.encode("token", "")
        } else {
            kv.encode("token", token)
        }
    }

    fun getToken(): String?  {
        val kv = MMKV.mmkvWithID("app")
        return kv.decodeString("token")
    }

}