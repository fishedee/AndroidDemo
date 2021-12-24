package com.example.myapplication
import android.R
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/** * Created by wnw on 16-5-22.  */
class LinearRecycleViewDecoration(private val mContext: Context, orientation: Int,drawableResource:Int) :
    ItemDecoration() {

    private val mDivider: Drawable

    private var mOrientation = 0

    init {
        mDivider = mContext.resources.getDrawable(drawableResource)
        setOrientation(orientation)
    }


    //设置屏幕的方向
    private fun setOrientation(orientation: Int) {
        require(!(orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST)) { "invalid orientation" }
        mOrientation = orientation
        Log.d("Decorator","height = ${mDivider.intrinsicHeight} and width = ${mDivider.intrinsicWidth}")
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (mOrientation == HORIZONTAL_LIST) {
            drawVerticalLine(c, parent, state)
        } else {
            drawHorizontalLine(c, parent, state)
        }
    }

    //画横线, 这里的parent其实是显示在屏幕显示的这部分
    private fun drawHorizontalLine(c: Canvas?, parent: RecyclerView, state: RecyclerView.State?) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            //获得child的布局信息
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider.intrinsicHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c!!)
            Log.d("Decorator", "drawHorizontalLine ${left}   ${right} ${top} ${bottom} ${i}");
        }
    }

    //画竖线
    private fun drawVerticalLine(c: Canvas?, parent: RecyclerView, state: RecyclerView.State?) {
        val top = parent.paddingTop
        val bottom = parent.height - parent.paddingBottom
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            //获得child的布局信息
            val params = child.layoutParams as RecyclerView.LayoutParams
            val left = child.right + params.rightMargin
            val right = left + mDivider.intrinsicWidth
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c!!)
            Log.d("Decorator", "drawVerticalLine ${left}   ${right} ${top} ${bottom} ${i}");
        }
    }

    //由于Divider也有长宽高，每一个Item需要向下或者向右偏移
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (mOrientation == HORIZONTAL_LIST) {
            //画横线，就是往下偏移一个分割线的高度
            outRect[0, 0, 0] = mDivider.intrinsicHeight
        } else {
            //画竖线，就是往右偏移一个分割线的宽度
            outRect[0, 0, mDivider.intrinsicWidth] = 0
        }
    }

    companion object {
        const val HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL
        const val VERTICAL_LIST = LinearLayoutManager.VERTICAL
    }
}
