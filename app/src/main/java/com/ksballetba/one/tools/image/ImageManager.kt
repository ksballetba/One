package com.ksballetba.one.tools.image

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.*
import android.support.annotation.IntRange

class ImageManager(val context: Context) {

    private var renderScript:RenderScript? = RenderScript.create(context)


    fun gaussianBlur(@IntRange(from = 1,to = 25)radius:Int,original:Bitmap):Bitmap{
        val input = Allocation.createFromBitmap(renderScript,original)
        val output = Allocation.createTyped(renderScript,input.type)
        val scriptInterinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript))
        scriptInterinsicBlur.setRadius(radius.toFloat())
        scriptInterinsicBlur.setInput(input)
        scriptInterinsicBlur.forEach(output)
        output.copyTo(original)
        return original
    }

}