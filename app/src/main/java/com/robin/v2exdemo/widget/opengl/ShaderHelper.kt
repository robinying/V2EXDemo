package com.robin.v2exdemo.widget.opengl

import android.content.Context
import android.opengl.GLES20.*
import android.util.Log
import com.blankj.utilcode.util.LogUtils
import com.robin.v2exdemo.app.util.OpenGlUtil
import com.robin.v2exdemo.app.util.OtherUtil


object ShaderHelper {
    private const val TAG = "ShaderHelper"
    fun compileVertexShader(vertextShaderSourceCode: String): Int {
        return compileShader(GL_VERTEX_SHADER, vertextShaderSourceCode)
    }

    fun compileFragmentShader(fragmentShaderSourceCode: String): Int {
        return compileShader(GL_FRAGMENT_SHADER, fragmentShaderSourceCode)
    }

    private fun compileShader(type: Int, shaderSourceCode: String): Int {

        // Id for OpenGL Shader Object
        val shaderObjectId = glCreateShader(type)
        if (shaderObjectId == 0) {

            Log.w(TAG, "Could not create new shader.")

            return 0
        }

        // Load Shader Source Code Into OpenGL Shader Object referenced by shaderObjectId
        glShaderSource(shaderObjectId, shaderSourceCode)

        // Compile the Shader Source Code loaded into the OpenGL Shader object in previous step
        glCompileShader(shaderObjectId)

        // Retrieve results of Shader Compilation from Shader Object.
        // Place result in int array (compileStatus) at index specified (0 in this case)
        val compileStatus = IntArray(1)
        glGetShaderiv(shaderObjectId, GL_COMPILE_STATUS, compileStatus, 0)

        // Display results of shader compilation
        Log.v(
            TAG, """
     Results of compiling shader source:
     $shaderSourceCode
     ${glGetShaderInfoLog(shaderObjectId)}
     """.trimIndent()
        )


        // Check for compilation failure
        if (compileStatus[0] == 0) {
            // Compilation failed, clean up shader memory
            glDeleteShader(shaderObjectId)

            Log.w(TAG, "Compilation of shader failed.")

            return 0
        }

        // Compilation didn't fail, so return shader object id
        return shaderObjectId
    }

    fun linkProgram(vertexShaderId: Int, fragmentShaderId: Int): Int {
        val programObjectId = glCreateProgram()

        // Check that a program was created
        if (programObjectId == 0) {

            Log.w(TAG, "Could not create new OpenGL program.")

            return 0
        }

        // Attached shaders to program
        glAttachShader(programObjectId, vertexShaderId)
        glAttachShader(programObjectId, fragmentShaderId)

        // Link the shaders in the program
        glLinkProgram(programObjectId)

        // Check that the program linking worked
        val linkStatus = IntArray(1)
        glGetProgramiv(programObjectId, GL_LINK_STATUS, linkStatus, 0)

        Log.v(
            TAG, """
     Results of linking program:
     ${glGetProgramInfoLog(programObjectId)}
     """.trimIndent()
        )

        if (linkStatus[0] == 0) {
            // Linking failed, so cleanup program memory
            glDeleteProgram(programObjectId)

            Log.w(TAG, "Linking of program failed.")

            return 0
        }

        // Return linked program with attached shaders
        return programObjectId
    }

    fun validateProgram(programObjectId: Int): Boolean {
        glValidateProgram(programObjectId)
        val validateStatus = IntArray(1)
        glGetProgramiv(programObjectId, GL_VALIDATE_STATUS, validateStatus, 0)
        Log.v(
            TAG, """Results of validating program: ${validateStatus[0]}
 Log: ${glGetProgramInfoLog(programObjectId)}"""
        )
        return validateStatus[0] != 0
    }

    fun buildProgram(vertexShaderSource: String, fragmentShaderSource: String): Int {
        val program: Int
        LogUtils.d("vertex is $vertexShaderSource frag is $fragmentShaderSource")
        val vertexShader = compileVertexShader(vertexShaderSource)
        val fragmentShader: Int = compileFragmentShader(fragmentShaderSource)
        program = linkProgram(vertexShader, fragmentShader)
        validateProgram(program)
        return program
    }


    fun buildProgram(context: Context, vertexShaderSource: Int, fragmentShaderSource: Int): Int {
        val vertexString: String =
            OpenGlUtil.readTextFromRaw(context, vertexShaderSource)
        val textureString: String =
            OpenGlUtil.readTextFromRaw(context, fragmentShaderSource)
        return buildProgram(vertexString, textureString)
    }

}