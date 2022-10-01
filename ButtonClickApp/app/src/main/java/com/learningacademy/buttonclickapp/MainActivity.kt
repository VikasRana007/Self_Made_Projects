package com.learningacademy.buttonclickapp

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private var userInput: EditText? = null
    private var button: Button? = null
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate : Called")
        setContentView(R.layout.activity_main)
        // R class files have all information about resources
        userInput = findViewById(R.id.editTextTextPersonName)
        userInput?.setText("")
        button = findViewById(R.id.button)
        textView = findViewById(R.id.textView)
        textView?.movementMethod = ScrollingMovementMethod()

        button?.setOnClickListener {
            Log.d(TAG, "onClick : Called")
            textView?.append(userInput?.text)
            textView?.append("\n")
            userInput?.text?.clear()
        }
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.d(TAG, "onRestore : Called")
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onRestart() {
        Log.d(TAG, "onRestart : Called")
        super.onRestart()
    }

    override fun onStart() {
        Log.d(TAG, "onStart : Called")
        super.onStart()
    }

    override fun onPause() {
        Log.d(TAG, "onPause : Called")
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(TAG, "onSaveInstanceState : Called")
        super.onSaveInstanceState(outState)
    }

    override fun onResume() {
        Log.d(TAG, "onResume : Called")
        super.onResume()
    }

    override fun onStop() {
        Log.d(TAG, "onStop : Called")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy : Called")
        super.onDestroy()
    }
}