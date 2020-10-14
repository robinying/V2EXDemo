package com.robin.v2exdemo.widget.opengl

import android.content.Context
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import me.hgj.jetpackmvvm.util.LogUtils
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class GLOneRender(context: Context) : GLSurfaceView.Renderer {

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        LogUtils.debugInfo("GLOneRender onSurfaceCreated $config")
        GLES20.glClearColor(0.8f, 0.4f, 0.5f, 0.3f)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        LogUtils.debugInfo("GLOneRender onSurfaceChanged width: $width ---height:$height")
        GLES20.glViewport(0, 0, width, height)
    }

    override fun onDrawFrame(gl: GL10?) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
    }
}