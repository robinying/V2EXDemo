package com.robin.v2exdemo.widget.opengl

import android.opengl.GLES20
import android.provider.SyncStateContract
import com.robin.v2exdemo.app.util.ConstantUtil
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

class VertexArray(vertexData: FloatArray) {
    private val floatBuffer: FloatBuffer

    /**
     * 使顶点某个数据使能的
     * @param dataOffset
     * @param attributeLocation
     * @param componentCount
     * @param stride
     */
    fun setVertexAttribPointer(
        dataOffset: Int,
        attributeLocation: Int,
        componentCount: Int,
        stride: Int
    ) {
        floatBuffer.position(dataOffset)
        GLES20.glVertexAttribPointer(
            attributeLocation,
            componentCount,
            GLES20.GL_FLOAT,
            false,
            stride,
            floatBuffer
        )
        GLES20.glEnableVertexAttribArray(attributeLocation)
        floatBuffer.position(0)
    }

    /**
     * 初始化顶点数据的
     * @param vertexData
     */
    init {
        floatBuffer = ByteBuffer
            .allocateDirect(vertexData.size * ConstantUtil.BYTES_PRE_FLOAT)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
            .put(vertexData)
    }
}
