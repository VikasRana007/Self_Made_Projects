package com.me.customviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.me.customviews.my_own_views.CustomView

class CustomViewActivity : AppCompatActivity() {

    private lateinit var customView: CustomView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view)
//        customView = findViewById(R.id.customView)
//
//        findViewById<Button>(R.id.btn_swap_color).setOnClickListener {
//            customView.swapColor()
//        }
    }
}