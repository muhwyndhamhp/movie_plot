package io.muhwyndhamhp.moviedb.ui.home

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.muhwyndhamhp.moviedb.R

class PagerDecorator(private val itemCount: Int, private val context: Context?) :
    RecyclerView.ItemDecoration() {

    private var dotStroke: Paint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 1f
        color = context?.resources?.getColor(R.color.button_grey) ?: Color.GRAY
    }

    private val dotFill = Paint().apply {
        style = Paint.Style.FILL
        color = context?.resources?.getColor(R.color.button_grey) ?: Color.GRAY
    }
    private val dots = mutableListOf<Pair<Float, Float>>()

    private val dotRadius = 15f
    private val dotPadding = 120f

    private var selectedDot = 0
    private var indicatorInitialized = false

    override fun onDrawOver(
        canvas: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        if (!indicatorInitialized) {
            setupIndicators(parent)
        }

        parent.adapter?.let {
            with(canvas) {
                for (i in 0 until itemCount) {
                    drawCircle(dots[i].first, dots[i].second)
                }
            }

            val visibleItem = (parent.layoutManager as LinearLayoutManager)
                .findFirstCompletelyVisibleItemPosition()

            if (visibleItem >= 0) {
                selectedDot = visibleItem
            }

            for (i in 0 until itemCount) {
                if (selectedDot == i) canvas.drawCircle(
                    dots[i].first,
                    dots[i].second,
                    true
                )
            }
        }
    }

    private fun Canvas.drawCircle(x: Float, y: Float, isFill: Boolean = false) {
        drawCircle(x, y, dotRadius, if (isFill) dotFill else dotStroke)
    }

    private fun setupIndicators(recyclerView: RecyclerView) {
        indicatorInitialized = true

        val indicatorTotalWidth = 2 * dotRadius + dotPadding
        val indicatorPosX =
            (recyclerView.width - indicatorTotalWidth * itemCount / 4) / 2f
        val indicatorPosY = recyclerView.height - (dotRadius * 6 / 2f)

        for (i in 0 until itemCount) {
            dots.add((indicatorPosX + i * dotRadius * 3) to indicatorPosY)
        }
    }

}