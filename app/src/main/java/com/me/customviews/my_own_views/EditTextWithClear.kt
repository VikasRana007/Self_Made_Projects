package com.me.customviews.my_own_views

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat
import com.me.customviews.R


class EditTextWithClear : AppCompatEditText {
    var clearButtonImage: Drawable? = null

    constructor(context: Context) : super(context) {
        initView(null)
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(attrs)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context,attrs,defStyleAttr) {
        initView(attrs)
    }

    private fun initView(set : AttributeSet?) {
        clearButtonImage = ResourcesCompat.getDrawable(resources, R.drawable.clear_text, null)


        setOnTouchListener(object : OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (compoundDrawablesRelative[2] != null) {
                    val clearButtonStart: Float // Used for LTR languages
                    val clearButtonEnd: Float // Used for RTL languages
                    var isClearButtonClicked = false
                    if (layoutDirection == LAYOUT_DIRECTION_RTL) {
                        // If RTL, get the end of the button on the left side.
                        clearButtonEnd = (clearButtonImage?.intrinsicWidth)?.plus(paddingStart)?.toFloat()!!
                        // If the touch occurred before the end of the button,
                        // set isClearButtonClicked to true.
                        if (event?.x!! < clearButtonEnd) {
                            isClearButtonClicked = true;
                        }
                    } else {
                        // Layout is LTR.
                        // Get the start of the button on the right side.
                        clearButtonStart = ((width - paddingEnd - clearButtonImage?.intrinsicWidth!!).toFloat())
                        // If the touch occurred after the start of the button,
                        // set isClearButtonClicked to true.
                        if (event?.x!! > clearButtonStart) {
                            isClearButtonClicked = true;
                        }
                    }


                    if (isClearButtonClicked) {
                        // Check for ACTION_DOWN (always occurs before ACTION_UP).
                        if (event.action == MotionEvent.ACTION_DOWN) {
                            // Switch to the black version of clear button.
                            clearButtonImage =
                                ResourcesCompat.getDrawable(
                                    resources,
                                    R.drawable.blur_clear, null)
                            showClearButton()
                        }
                        // Check for ACTION_UP.
                        if (event.action == MotionEvent.ACTION_UP) {
                            // Switch to the opaque version of clear button.
                            clearButtonImage =
                                ResourcesCompat.getDrawable(
                                    resources,
                                    R.drawable.clear_text, null)
                            // Clear the text and hide the clear button.
                            text?.clear()
                            hideClearButton()
                            return true
                        }
                    } else {
                        return false
                    }

                }
                return false
            }

        })


        addTextChangedListener(object  : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                showClearButton()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }


    private fun showClearButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds(
            null,    // start of text
            null,      // Above text
            clearButtonImage,   // End of text
            null      // Below text
        )
    }

    private fun hideClearButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds(
            null,    // start of text
            null,      // Above text
            null ,   // End of text
            null      // Below text
        )
    }

}