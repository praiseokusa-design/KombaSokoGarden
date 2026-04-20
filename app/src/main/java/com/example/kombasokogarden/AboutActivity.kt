package com.example.kombasokogarden

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class AboutActivity : AppCompatActivity() {
    //declare tts variable
    lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    //find view
        val tvAbout = findViewById<TextView>(R.id.tv_about)
        val speakBtn = findViewById<Button>(R.id.btn_speak)

        //create tts object check if tts is available  and set the language
        tts = TextToSpeech(this) {
            //if tts is available
            if (it == TextToSpeech.SUCCESS) {
                tts.language = Locale.US
            }
        }
        //set on click listiner
        speakBtn.setOnClickListener {
            val text=tvAbout.text.toString()
            //ask tts object to the text from the tvAbout
            tts.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
        }
        }
    //stop tts from speaking when app is on
    override fun onDestroy() {
        super.onDestroy()
        tts.stop()
        tts.shutdown()

    }
}