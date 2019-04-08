package com.croquis.zigzag_shop_login.extension

import android.graphics.*

fun getRoundedBitmap(bitmap: Bitmap): Bitmap {
    val output = Bitmap.createBitmap(
            bitmap.width,
            bitmap.height,
            Bitmap.Config.ARGB_8888)
    val canvas = Canvas(output)

    val color = Color.WHITE
    val paint = Paint()
    val rect = Rect(0, 0, bitmap.width, bitmap.height)

    paint.isAntiAlias = true
    paint.color = color

    canvas.drawCircle(
            (bitmap.width / 2).toFloat(),
            (bitmap.height / 2).toFloat(),
            (bitmap.width / 2).toFloat(),
            paint)
    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    canvas.drawBitmap(bitmap, rect, rect, paint)

    return output
}