package com.me.customviews.my_own_views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.accessibility.AccessibilityNodeInfo
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import com.me.customviews.R
import java.lang.Integer.min

private enum class FanSpeed(val label: Int) {
    OFF(R.string.fan_off),
    LOW(R.string.fan_low),
    MEDIUM(R.string.fan_medium),
    HIGH(R.string.fan_high);

    fun next() = when (this) {
        OFF -> LOW
        LOW -> MEDIUM
        MEDIUM -> HIGH
        HIGH -> OFF
    }
}

private const val RADIUS_OFFSET_LABEL = 50
private const val RADIUS_OFFSET_INDICATOR = -45

class MyCustomButton @JvmOverloads constructor(context: Context?,
                                               attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {

    private var radius = 0.0f
    private var fanSpeed = FanSpeed.OFF
    private val pointPosition: PointF = PointF(0.0f, 0.0f)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 45.0f
        typeface = Typeface.create("", Typeface.BOLD)
    }




    init {
        isClickable = true

        // For minsdk >= 21, you can just add a click action. In this app since minSdk is 19,
        // you must add a delegate to handle accessibility.
        ViewCompat.setAccessibilityDelegate(this, object : AccessibilityDelegateCompat() {
            override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                val customClick = AccessibilityNodeInfoCompat.AccessibilityActionCompat(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    // If the fan speed is OFF, LOW, or MEDIUM, the hint is to change the speed.
                    // If it is HIGH use reset.
                    context?.getString(if (fanSpeed !=  FanSpeed.HIGH) R.string.change else R.string.reset)
                )
                info.addAction(customClick)
            }
        })
    }

    override fun performClick(): Boolean {
        //        // Give default click listeners priority and perform accessibility/autofill events.
//        // Also calls onClickListener() to handle further subclass customizations.
        if (super.performClick()) return true

        // Rotates between each of the different selection
        // states on each click.
        fanSpeed = fanSpeed.next()
//        updateContentDescription()
        // Redraw the view.
        invalidate()
        return true
    }

    override fun onSizeChanged(width: Int, height: Int, oldwidth: Int, oldheight: Int) {
//        super.onSizeChanged(w, h, oldw, oldh)
        radius = (min(width, height) / 2 * 0.7).toFloat()

    }

    private fun PointF.computeXYForSpeed(pos: FanSpeed, radius: Float) {
        // Angles are in radians.
        val startAngle = Math.PI * (9 / 8.0)
        val angle = startAngle + pos.ordinal * (Math.PI / 4)
        x = (radius * kotlin.math.cos(angle)).toFloat() + width / 2
        y = (radius * kotlin.math.sin(angle)).toFloat() + height / 2
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = if (fanSpeed == FanSpeed.OFF) Color.GRAY else Color.GREEN

        // draw the dial
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, paint)

        // draw the indicator circle
        val markerRadius = radius + RADIUS_OFFSET_INDICATOR
        pointPosition.computeXYForSpeed(fanSpeed, markerRadius)
        paint.color = Color.BLACK
        canvas.drawCircle(pointPosition.x, pointPosition.y, radius / 12, paint)

        // draw the text label
        val labelRadius = radius + RADIUS_OFFSET_LABEL
        for (i in FanSpeed.values()) {
            pointPosition.computeXYForSpeed(i, labelRadius)
            val label = resources.getString(i.label)
            canvas.drawText(label, pointPosition.x, pointPosition.y, paint)
        }
    }
}