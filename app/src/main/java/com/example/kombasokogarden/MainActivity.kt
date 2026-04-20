package com.example.kombasokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//    Find views by their IDs

        val signUp= findViewById<Button>(R.id.Sign_Up)
        signUp.setOnClickListener {
//            intent
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }

        val signIn= findViewById<Button>(R.id.Sign_In)
        signIn.setOnClickListener {
//            intent
            val intent = Intent(this, Signin::class.java)
            startActivity(intent)
        }

        val aboutBtn= findViewById<Button>(R.id.about)
        aboutBtn.setOnClickListener {
//            intent
            val intent = Intent(applicationContext, AboutActivity::class.java)
            startActivity(intent)
        }

        val speakBtn= findViewById<Button>(R.id.speechtotext)
        speakBtn.setOnClickListener {
//            intent
            val intent = Intent(applicationContext, SpeachToTextActivity::class.java)
            startActivity(intent)
        }

//        find the view
        val progressBar = findViewById<ProgressBar>(R.id.progressbar)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val url = "https://kus-kus.alwaysdata.net/api/get_product_details"

//        helper object instance from class ApiHelper
        val helper = ApiHelper(applicationContext)
        helper.loadProducts(url, recyclerView, progressBar)



    }

    }
