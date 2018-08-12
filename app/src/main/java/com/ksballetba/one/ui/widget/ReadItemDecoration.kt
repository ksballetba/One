package com.ksballetba.one.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class ReadItemDecoration:RecyclerView.ItemDecoration {
    private var mContext:Context? = null
    private var mDivider:Drawable? = null
    private var mOrientation:Int? = null
    companion object {
        val HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL
        val VERTICAL_LIST = LinearLayoutManager.VERTICAL
        val ATTRS = intArrayOf(android.R.attr.listDivider)
    }

    constructor(context: Context,orientation: Int){
        mContext = context
        val ta = context.obtainStyledAttributes(ATTRS)
        mDivider = ta.getDrawable(0)
        ta.recycle()
        setOrientation(orientation)
    }

    fun setOrientation(orientation: Int){
        if(orientation!= HORIZONTAL_LIST&&orientation!= VERTICAL_LIST){
            throw IllegalArgumentException("invalid orientation")
        }
        mOrientation = orientation
    }

    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        if(mOrientation == HORIZONTAL_LIST){

        }
    }
}