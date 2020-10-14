package com.robin.v2exdemo.app.util

import android.content.Context
import android.content.pm.ConfigurationInfo
import android.os.Build
import com.hgj.jetpackmvvm.util.activityManager
import java.io.BufferedReader
import java.io.InputStreamReader

object OpenGlUtil {
    fun readTextFromRaw(context: Context, resourceId: Int): String {
        val sb = StringBuilder()
        try {
            val inputStream = context.resources.openRawResource(resourceId)
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            var line: String
            while (bufferedReader.readLine().also { line = it } != null) {
                sb.append(line)
                sb.append("\n")
            }
        } catch (e: Exception) {

        }
        return sb.toString()
    }

    fun supportsEs2(context:Context): Boolean {
        val configurationInfo: ConfigurationInfo = context.activityManager.deviceConfigurationInfo
        return (configurationInfo.reqGlEsVersion >= 0x20000
                || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1))
    }

}