package com.robin.v2exdemo.widget.customview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.Nullable
import com.robin.v2exdemo.R

class RoundRectView(context: Context, @Nullable attrs: AttributeSet?) :
    View(context, attrs) {
    private val fillPaint = Paint()

    var radius: Float = 0f


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
        val rect = RectF(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat())
        canvas.drawRoundRect(rect, radius, radius, fillPaint)
    }


    init {
        fillPaint.style = Paint.Style.FILL
        fillPaint.isAntiAlias = true
        fillPaint.color = Color.DKGRAY
        val a = context.obtainStyledAttributes(attrs, R.styleable.RoundRectView)
        color = a.getColor(R.styleable.RoundRectView_roundRectColor, Color.BLACK)
        radius = a.getDimension(R.styleable.RoundRectView_roundRectRadius, 0f)
        a.recycle()
    }


    override fun onMeasure(
        widthMeasureSpec: Int,
        heightMeasureSpec: Int
    ) = super.onMeasure(widthMeasureSpec, widthMeasureSpec)
}
