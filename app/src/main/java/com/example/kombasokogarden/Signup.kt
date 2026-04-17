package com.example.kombasokogarden

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.loopj.android.http.RequestParams

class Signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val username = findViewById<TextInputEditText>(R.id.username)
        val email = findViewById<TextInputEditText>(R.id.email)
        val phone = findViewById<TextInputEditText>(R.id.phone)
        val password = findViewById<TextInputEditText>(R.id.password)
        val signupBtn = findViewById<Button>(R.id.signup_btn)

        signupBtn.setOnClickListener {
            val api="https://kus-kus.alwaysdata.net/api/signup"
            val data=RequestParams()
            data.put("username",username.text.toString().trim())
            data.put("email",email.text.toString().trim())
            data.put("phone",phone.text.toString().trim())
            data.put("password",password.text.toString().trim())
            val helper=ApiHelper(applicationContext)
            helper.post(api,data)

        }
    }
}