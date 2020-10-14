package com.robin.v2exdemo.widget.opengl

import android.content.Context
import android.opengl.GLES20.*
import android.opengl.GLSurfaceView
import com.blankj.utilcode.util.LogUtils
import com.robin.v2exdemo.R
import com.robin.v2exdemo.app.util.OpenGlUtil
import com.robin.v2exdemo.app.util.OtherUtil
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10


class AirHockeyRender(context: Context) : GLSurfaceView.Renderer {
    val POSITION_COMPONENT_COUNT = 2
    val BYTE_PER_FLOAT = 4
    val U_COLOR="u_Color"
    var uColorLocation:Int =0
    private val A_POSITION = "a_Position"
    private var aPositionLocation = 0
    var vertexData: FloatBuffer
    var mContext: Context = context
    var program: Int = 0
    var tableVertices = floatArrayOf(
        // First Triangle
        -0.7f, -0.7f,
        0.7f, 0.7f,
        -0.7f, 0.7f,
        // Second Triangle
        -0.7f, -0.7f,
        0.7f, -0.7f,
        0.7f, 0.7f,
        // Center Line
        -0.7f, 0f,
        0.7f, 0f,
        // Mallets
        0f, -0.35f,
        0f, 0.35f
    )

    init {
        vertexData = ByteBuffer.allocateDirect(tableVertices.size * BYTE_PER_FLOAT)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
        vertexData.put(tableVertices)
    }


    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f)

        val vertexCode = OpenGlUtil.readTextFromRaw(mContext, R.raw.simple_vertex_shader)
        val fragmentCode = OpenGlUtil.readTextFromRaw(mContext, R.raw.simple_fragment_shader)
        val vertexShader = ShaderHelper.compileVertexShader(vertexCode)
        val fragmentShader = ShaderHelper.compileFragmentShader(fragmentCode)
        program = ShaderHelper.linkProgram(vertexShader, fragmentShader)
        val isValid = ShaderHelper.validateProgram(program)
        LogUtils.d("robinTest isValid : $isValid")
        // Set OpenGL program to be used for any drawing
        glUseProgram(program)

        // Get Uniform Location
        uColorLocation = glGetUniformLocation(program, U_COLOR)

        // Get Attribute Location Once Shaders have been bound
        aPositionLocation = glGetAttribLocation(program, A_POSITION)

        // Tell OpenGL where to find data for our attribute a_Position
        // ===========================================================
        // Ensure OpenGL starts at beginning of data buffer
        vertexData.position(0)

        // Tell OpenGL that it can find data for a_Position in vertexData
        // glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, Buffer ptr)
        glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GL_FLOAT, false, 0, vertexData)

        // Enable Vertex Data Array so OpenGL can find all data
        glEnableVertexAttribArray(aPositionLocation)

    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        glViewport(0, 0, width, height);
    }

    override fun onDrawFrame(gl: GL10?) {
        // Clear the rendering surface.
        // This will wipe out all colors on the screen and fill the screen with
        // the color previously defined by our call to glClearColor.
        glClear(GL_COLOR_BUFFER_BIT)


        // Draw the Air Hockey Table
        // =========================

        // Set the color to white
        glUniform4f(uColorLocation, 1.0f, 1.0f, 1.0f, 1.0f)
        // Set drawing mode to Triangles, start reading vertices from beginning of array, read (and draw) 6 vertices
        // This draws First Triangle and Second Triangle because glVertexAttribPointer() told OpenGL that each vertex consists of 2 components
        glDrawArrays(GL_TRIANGLES, 0, 6)

        // Draw the Dividing Line (aka Center Ice)
        // =======================================
        // Set the color to red
        glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f)
        // Draw lines, starting at 6th index of array, draw two vertices
        glDrawArrays(GL_LINES, 6, 2)

        // Draw The Mallets
        // ================
        // Draw first mallet as blue, from 8th index, and 1 vertex
        glUniform4f(uColorLocation, 0.0f, 0.0f, 1.0f, 1.0f)
        glDrawArrays(GL_POINTS, 8, 1)

        // Draw second mallet as red, from 9th index, and 1 vertex
        glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f)
        glDrawArrays(GL_POINTS, 9, 1)
    }
}