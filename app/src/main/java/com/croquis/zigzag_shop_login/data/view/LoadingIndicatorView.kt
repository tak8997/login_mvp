package com.croquis.zigzag_shop_login.data.view

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.animation.RotateAnimation
import android.widget.RelativeLayout


internal class LoadingIndicatorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null

) : RelativeLayout(context, attrs) {

    private lateinit var arrBars: ArrayList<LoadingIndicatorBarView>

    private val numberOfBars: Int = 12
    private val radius: Float = 45.0F

    private var isAnimating: Boolean = false
    private var currentFrame: Int = 0
    private var playFrameRunnable: Runnable? = null

    init {
        setupViews()
        setupLayouts()
        addViews()
        spreadBars()
    }

    fun startAnimating() {
        alpha = 1.0f

        isAnimating = true

        playFrameRunnable = Runnable { playFrame() }

        playFrame()
    }

    fun stopAnimating() {
        isAnimating = false

        alpha = 0.0f

        invalidate()

        playFrameRunnable = null
    }

    private fun setupViews() {
        arrBars = ArrayList()

        for (i in 0 until numberOfBars) {
            val bar = LoadingIndicatorBarView(context)

            arrBars.add(bar)
        }
    }

    private fun setupLayouts() {
        for (i in 0 until numberOfBars) {
            val bar = arrBars[i]

            val barLayoutParams = RelativeLayout.LayoutParams(
                (radius / 5.0f).toInt(),
                (radius / 2.0f).toInt()
            )
            barLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL)

            bar.layoutParams = barLayoutParams
        }
    }

    private fun addViews() {
        for (i in 0 until numberOfBars) {
            val bar = arrBars[i]
            addView(bar)
        }
    }

    private fun spreadBars() {
        var degrees = 0

        for (i in 0 until arrBars.size) {
            val bar = arrBars[i]
            rotateBar(bar, degrees.toFloat())

            degrees += 30
        }
    }

    private fun rotateBar(bar: LoadingIndicatorBarView, degrees: Float) {
        val animation = RotateAnimation(0f, degrees, radius / 10.0f, radius)
        animation.duration = 1
        animation.fillAfter = true

        bar.animation = animation
        animation.start()
    }

    private fun playFrame() {
        if (isAnimating) {
            resetAllBarAlpha()
            updateFrame()

            Handler().postDelayed(playFrameRunnable, 90)
        }
    }

    private fun updateFrame() {
        if (isAnimating) {
            showFrame(currentFrame)
            currentFrame += 1

            if (currentFrame > 11) {
                currentFrame = 0
            }
        }
    }

    private fun resetAllBarAlpha() {
        for (i in 0 until arrBars.size) {
            val bar = arrBars[i]
            bar.alpha = 0.5f
        }
    }

    private fun showFrame(frameNumber: Int) {
        val indexes = getFrameIndexesForFrameNumber(frameNumber)

        gradientColorBarSets(indexes)
    }

    private fun getFrameIndexesForFrameNumber(frameNumber: Int): IntArray {
        return when (frameNumber) {
            0 -> indexesFromNumbers(0, 11, 10, 9)
            1 -> indexesFromNumbers(1, 0, 11, 10)
            2 -> indexesFromNumbers(2, 1, 0, 11)
            3 -> indexesFromNumbers(3, 2, 1, 0)
            4 -> indexesFromNumbers(4, 3, 2, 1)
            5 -> indexesFromNumbers(5, 4, 3, 2)
            6 -> indexesFromNumbers(6, 5, 4, 3)
            7 -> indexesFromNumbers(7, 6, 5, 4)
            8 -> indexesFromNumbers(8, 7, 6, 5)
            9 -> indexesFromNumbers(9, 8, 7, 6)
            10 -> indexesFromNumbers(10, 9, 8, 7)
            else -> indexesFromNumbers(11, 10, 9, 8)
        }
    }

    private fun indexesFromNumbers(i1: Int, i2: Int, i3: Int, i4: Int): IntArray {
        return intArrayOf(i1, i2, i3, i4)
    }

    private fun gradientColorBarSets(indexes: IntArray) {
        var alpha = 1.0f

        for (i in indexes.indices) {
            val barIndex = indexes[i]
            val barView = arrBars[barIndex]

            barView.alpha = alpha
            alpha -= 0.125f
        }

        invalidate()
    }
}