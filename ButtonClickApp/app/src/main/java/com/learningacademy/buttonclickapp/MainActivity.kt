package com.learningacademy.buttonclickapp

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var userInput: EditText? = null
    private var button: Button? = null
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // R class files have all information about resources
        userInput = findViewById(R.id.editTextTextPersonName)
        userInput?.setText("")
        button = findViewById(R.id.button)
        textView = findViewById(R.id.textView)
        textView?.movementMethod = ScrollingMovementMethod()

        button?.setOnClickListener {
            textView?.append(userInput?.text)
            textView?.append("\n")
            userInput?.text?.clear()
        }
    }
}