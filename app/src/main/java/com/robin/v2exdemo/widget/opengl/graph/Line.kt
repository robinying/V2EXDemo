package com.robin.v2exdemo.widget.opengl.graph

import android.content.Context
import android.opengl.GLES20
import android.opengl.Matrix
import com.blankj.utilcode.util.LogUtils
import com.robin.v2exdemo.R
import com.robin.v2exdemo.widget.opengl.BaseShape
import com.robin.v2exdemo.widget.opengl.ShaderHelper
import com.robin.v2exdemo.widget.opengl.VertexArray
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class Line(context: Context) : BaseShape(context) {
    var lineVertex = floatArrayOf(
        -1f, 1f,
        1f, -1f
    )
    private var uProjectionMatrixLocation = 0
    private var uViewMatrixLocation = 0
    private var aColorLocation = 0
    private var aPositionLocation = 0
    private var uMatrixLocation = 0

    companion object {
        private const val U_COLOR = "u_Color"
        private const val A_POSITION = "a_Position"
        private const val U_MODEL_MATRIX = "u_ModelMatrix"
        private const val U_PROJECTION_MATRIX = "u_ProjectionMatrix"
        private const val U_VIEW_MATRIX = "u_ViewMatrix"
    }

    init {
        mProgram = ShaderHelper.buildProgram(
            context, R.raw.line_vertex_shader, R.raw.line_fragment_shader
        )
        GLES20.glUseProgram(mProgram)
        vertexArray = VertexArray(lineVertex)
        POSITION_COMPONENT_COUNT = 2
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {

        aColorLocation = GLES20.glGetUniformLocation(mProgram, U_COLOR)
        aPositionLocation = GLES20.glGetAttribLocation(mProgram, A_POSITION)
        uMatrixLocation = GLES20.glGetUniformLocation(mProgram, U_MODEL_MATRIX)
        uProjectionMatrixLocation = GLES20.glGetUniformLocation(mProgram, U_PROJECTION_MATRIX)
        uViewMatrixLocation = GLES20.glGetUniformLocation(mProgram, U_VIEW_MATRIX)
        vertexArray!!.setVertexAttribPointer(0, aPositionLocation, POSITION_COMPONENT_COUNT, 0)
        Matrix.setIdentityM(modelMatrix, 0)
        Matrix.setIdentityM(viewMatrix, 0)
        Matrix.translateM(modelMatrix, 0, 0.5f, 0f, 0f)
        Matrix.setLookAtM(viewMatrix, 0, 0f, 0f, 10f, 0f, 0f, 0f, 0f, 1.0f, 0.0f)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        super.onSurfaceChanged(gl, width, height)
        LogUtils.d("width is $width height is $height")
        Matrix.perspectiveM(projectionMatrix, 0, 5f, width.toFloat() / height.toFloat(), 9f, 20f)

//        float aspectRatio = width > height ? (float) width / (float) height : (float) height / (float) width;
//
//        if (width > height){
//
//            Matrix.frustumM(projectionMatrix,0,-aspectRatio,aspectRatio,-1f,1f,5f,20f);
//
//        }else {
//
//            Matrix.frustumM(projectionMatrix,0,-1f,1f,-aspectRatio,aspectRatio,5f,20f);
//
//        }
    }

    override fun onDrawFrame(gl: GL10?) {
        super.onDrawFrame(gl)
        GLES20.glUniform4f(aColorLocation, 0.0f, 0.0f, 1.0f, 1.0f)

        // 使用矩阵平移，将坐标 x 轴平移 0.5 个单位
        GLES20.glUniformMatrix4fv(uMatrixLocation, 1, false, modelMatrix, 0)
        GLES20.glUniformMatrix4fv(uProjectionMatrixLocation, 1, false, projectionMatrix, 0)
        GLES20.glUniformMatrix4fv(uViewMatrixLocation, 1, false, viewMatrix, 0)
        GLES20.glDrawArrays(GLES20.GL_LINES, 0, 2)
    }

}
