package com.learningacademy.buttonclickapp

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var userInput: EditText?=null
    private var button: Button?=null
    private var textView: TextView?=null
    private var numTimesClicked = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // R class files have all information about resources
        userInput = findViewById(R.id.editTextTextPersonName)
        button = findViewById(R.id.button)
        textView = findViewById(R.id.textView)
        textView?.movementMethod = ScrollingMovementMethod()

        button?.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                numTimesClicked += 1
                if(numTimesClicked != 1){
                    textView?.append("The Button got tapped $numTimesClicked time")
                    textView?.append("s\n")
                }else{
                    textView?.append("The Button got tapped $numTimesClicked time\n")
                }


            }
        }) }
    }