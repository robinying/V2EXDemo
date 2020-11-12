package com.robin.v2exdemo.widget.customview

import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.Nullable


class CircleView(context: Context?, @Nullable attrs: AttributeSet?) :
    View(context, attrs) {
    private val fillPaint = Paint()

    @ColorInt
    var color: Int = Color.BLACK
        set(value) {
            field = value
            fillPaint.color = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCircle(canvas)
    }

    private fun drawCircle(canvas: Canvas) {
        canvas.drawCircle(
            measuredWidth / 2f,
            measuredHeight / 2f,
            (measuredWidth / 2f),
            fillPaint
        )
    }


    init {
        fillPaint.style = Paint.Style.FILL
        fillPaint.isAntiAlias = true
        fillPaint.color = Color.DKGRAY
    }


    override fun onMeasure(
        widthMeasureSpec: Int,
        heightMeasureSpec: Int
    ) = super.onMeasure(widthMeasureSpec, widthMeasureSpec)
}
