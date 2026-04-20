package com.example.kombasokogarden

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class SpeachToTextActivity : AppCompatActivity() {
    lateinit var speechTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_speach_to_text)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    //Find Button by ID
        val speakBtn = findViewById<Button>(R.id.btnSpeak)
        speechTextView = findViewById<TextView>(R.id.textview)
        //set on click listener
        speakBtn.setOnClickListener {
            //speak now fun
            speakNow()
        }
    }

    private fun speakNow() {

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now...")



        try {

            startActivityForResult(intent, 100)

        } catch (e: Exception) {

            Toast.makeText(this, "Speech recognition not supported", Toast.LENGTH_SHORT).show()

        }

    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)



        if (requestCode == 100 && resultCode == Activity.RESULT_OK && data != null) {

            val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

            speechTextView.text = result?.get(0) ?: "Couldn't recognize speech"

        }
    }
}