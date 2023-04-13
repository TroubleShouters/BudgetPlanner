package com.troubleshouters.budgetplanner.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecoration(context: Context, drawableId: Int) : RecyclerView.ItemDecoration() {

    private var divider: Drawable? = null

    init {
        divider = ContextCompat.getDrawable(context, drawableId)
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        for (i in 0 until parent.childCount) {
            val child: View = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + divider!!.intrinsicHeight
            divider!!.setBounds(left, top, right, bottom)
            divider!!.draw(canvas)
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = divider!!.intrinsicHeight
    }
}
