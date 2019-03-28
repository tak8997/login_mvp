package com.croquis.zigzag_shop_login.data.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.util.AttributeSet
import android.widget.RelativeLayout


internal class LoadingIndicatorBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null

) : RelativeLayout(context, attrs) {

    private val cornerRadius: Float = 4.0F

    init {
        setupViews()
    }

    private fun setupViews() {
        background = roundedCornerRectWithColor(
            Color.argb(255, 255, 255, 255), cornerRadius
        )

        alpha = 0.5f
    }

    private fun roundedCornerRectWithColor(color: Int, cornerRadius: Float): ShapeDrawable {
        val radii = floatArrayOf(cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius)

        val roundedCornerShape = RoundRectShape(radii, null, null)

        return ShapeDrawable().apply {
            paint.color = color
            shape = roundedCornerShape
        }
    }
}