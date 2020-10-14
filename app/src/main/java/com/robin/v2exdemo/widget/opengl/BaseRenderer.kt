package com.robin.v2exdemo.widget.opengl

import android.content.Context
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

abstract class BaseRenderer(protected var mContext: Context) : GLSurfaceView.Renderer {
    protected var modelMatrix = FloatArray(16)
    protected var viewMatrix = FloatArray(16)
    protected var projectionMatrix = FloatArray(16)
    protected var mvpMatrix = FloatArray(16)
    override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {}

    /**
     * Surface 刚创建的时候，它的 size 是 0，也就是说在画第一次图之前它会被调用一次
     *
     * @param gl
     * @param width
     * @param height
     */
    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {}
    override fun onDrawFrame(gl: GL10) {}
    open fun onSurfaceDestroyed() {}
}
