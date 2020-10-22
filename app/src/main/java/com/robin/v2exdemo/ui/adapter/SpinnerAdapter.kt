package com.robin.v2exdemo.ui.adapter

import android.R
import android.content.Context
import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.widget.ThemedSpinnerAdapter


class SpinnerAdapter constructor(
    context: Context,
    data: Array<String>
) :
    ArrayAdapter<String?>(
        context,
        R.layout.simple_list_item_1,
        data
    ),
    ThemedSpinnerAdapter {
    private val mDropDownHelper: ThemedSpinnerAdapter.Helper = ThemedSpinnerAdapter.Helper(context)
    override fun getDropDownView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val view: View
        view = if (convertView == null) {
            // Inflate the drop down using the helper's LayoutInflater
            val inflater = mDropDownHelper.dropDownViewInflater
            inflater.inflate(R.layout.simple_list_item_1, parent, false)
        } else {
            convertView
        }
        val textView = view.findViewById<TextView>(R.id.text1)
        textView.text = getItem(position)
        return view
    }

    override fun getDropDownViewTheme(): Resources.Theme? {
        return mDropDownHelper.dropDownViewTheme
    }

    override fun setDropDownViewTheme(theme: Resources.Theme?) {
        mDropDownHelper.dropDownViewTheme = theme
    }

}