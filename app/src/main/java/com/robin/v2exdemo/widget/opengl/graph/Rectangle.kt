package com.robin.v2exdemo.widget.opengl.graph

import android.content.Context
import android.opengl.GLES20
import com.robin.v2exdemo.R
import com.robin.v2exdemo.widget.opengl.BaseShape
import com.robin.v2exdemo.widget.opengl.ShaderHelper

class Rectangle(context: Context) : BaseShape(context) {

    init {
        mProgram =
            ShaderHelper.buildProgram(context, R.raw.line_vertex_shader, R.raw.line_fragment_shader)
        GLES20.glUseProgram(mProgram)
    }
}