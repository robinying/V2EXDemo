package com.robin.v2exdemo.widget.opengl

import android.content.Context
import com.robin.v2exdemo.widget.opengl.graph.Point

import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class BasicShapeRender(context: Context) : BaseRenderer(context) {

    lateinit var shape: BaseShape

    var clazz: Class<out BaseShape> = Point::class.java

    fun setShape(shape: Class<out BaseShape>) {
        this.clazz = shape
    }

    override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
        super.onSurfaceCreated(gl, config)

        try {
            val constructor = clazz.getDeclaredConstructor(Context::class.java)
            constructor.isAccessible = true
            shape = constructor.newInstance(mContext) as BaseShape
        } catch (e: Exception) {
            shape = Point(mContext)
        }

        shape.onSurfaceCreated(gl, config)

    }

    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        shape.onSurfaceChanged(gl, width, height)
    }

    override fun onDrawFrame(gl: GL10) {
        shape.onDrawFrame(gl)
    }

    override fun onSurfaceDestroyed() {
        shape.onSurfaceDestroyed()
    }


}