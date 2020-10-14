package com.robin.v2exdemo.widget.opengl.graph

import android.content.Context
import android.opengl.GLES20
import com.robin.v2exdemo.R
import com.robin.v2exdemo.widget.opengl.BaseShape
import com.robin.v2exdemo.widget.opengl.ShaderHelper
import com.robin.v2exdemo.widget.opengl.VertexArray
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class Point(context: Context) : BaseShape(context) {
    private var aColorLocation = 0
    private var aPositionLocation = 0
    var pointVertex = floatArrayOf(
        0f, 0f,
        0.1f, 0.1f,
        -0.1f, -0.1f,
        0.2f, 0.2f,
        0.3f, 0.3f,
        0.4f, 0.4f,
        0.5f, 0.5f,
        0.6f, 0.6f
    )

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        //绑定值
        aColorLocation = GLES20.glGetUniformLocation(mProgram, U_COLOR)
        aPositionLocation = GLES20.glGetAttribLocation(mProgram, A_POSITION)

        // 给绑定的值赋值，也就是从顶点数据那里开始读取，每次读取间隔是多少
        vertexArray?.setVertexAttribPointer(
            0, aPositionLocation, POSITION_COMPONENT_COUNT,
            0
        )
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        super.onSurfaceChanged(gl, width, height)
        GLES20.glViewport(0, 0, width, height)
    }

    override fun onDrawFrame(gl: GL10?) {
        // 给绑定的值赋值
        GLES20.glUniform4f(aColorLocation, 0.0f, 0.0f, 1.0f, 1.0f)
        GLES20.glDrawArrays(GLES20.GL_POINTS, 0, pointVertex.size / POSITION_COMPONENT_COUNT)
    }

    override fun onDrawFrame(gl: GL10?, mvpMatrix: FloatArray?) {
        super.onDrawFrame(gl, mvpMatrix)
    }

    override fun onSurfaceDestroyed() {
        super.onSurfaceDestroyed()
        GLES20.glDeleteProgram(mProgram)
    }

    companion object {
        // 着色器中定义的变量，在 Java 层绑定并赋值
        private const val U_COLOR = "u_Color"
        private const val A_POSITION = "a_Position"
    }

    init {
        mProgram = ShaderHelper.buildProgram(
            context, R.raw.point_vertex_shader, R.raw.point_fragment_shader
        )
        GLES20.glUseProgram(mProgram)
        vertexArray = VertexArray(pointVertex)
        POSITION_COMPONENT_COUNT = 2
    }
}
