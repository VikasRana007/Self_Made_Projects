package com.me.customviews.my_own_views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.me.customviews.R

class MyCustomTextField @JvmOverloads constructor(

    context: Context, attrs: AttributeSet? = null) : AppCompatEditText(context, attrs) {
    private lateinit var paint: Paint
        init {
            initPaint()
        }

    private fun initPaint(){
        this.paint = Paint()
        paint.letterSpacing = 0.045f


    }



    override fun onDraw(canvas: Canvas?) {
        setBackgroundDrawable(context.getDrawable(R.drawable.custom_edit_text))
//        supportBackgroundTintMode = PorterDuff.Mode.CLEAR

        super.onDraw(canvas)
    }




    }