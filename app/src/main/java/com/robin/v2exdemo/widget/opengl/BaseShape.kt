package com.robin.v2exdemo.widget.opengl

import android.content.Context
import android.opengl.GLES20
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

abstract class BaseShape(protected var mContext: Context) {
    protected var vertexArray: VertexArray? = null
    protected var indexArray: VertexArray? = null
    protected var mProgram = 0
    protected var modelMatrix = FloatArray(16)
    protected var viewMatrix = FloatArray(16)
    protected var projectionMatrix = FloatArray(16)
    protected var mvpMatrix = FloatArray(16)
    protected var POSITION_COMPONENT_COUNT = 0
    protected var TEXTURE_COORDINATES_COMPONENT_COUNT = 2
    protected var STRIDE = 0
    open fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {}
    open fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {}
    open fun onDrawFrame(gl: GL10?) {
        GLES20.glClearColor(0f, 0f, 0f, 1f)
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT or GLES20.GL_COLOR_BUFFER_BIT)
    }

    open fun onDrawFrame(gl: GL10?, mvpMatrix: FloatArray?) {
        GLES20.glClearColor(0f, 0f, 0f, 1f)
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT or GLES20.GL_COLOR_BUFFER_BIT)
    }

    open fun onSurfaceDestroyed() {}
}
