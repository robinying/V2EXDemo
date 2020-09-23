package com.robin.v2exdemo.app.util

import android.content.Context
import android.content.Intent
import android.net.Uri

object OtherUtil {

    fun callPhone(context: Context, phoneNum: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        val data: Uri = Uri.parse("tel:$phoneNum")
        intent.data = data
        context.startActivity(intent)
    }
}