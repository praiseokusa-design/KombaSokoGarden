package com.example.kombasokogarden

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText
import com.loopj.android.http.RequestParams

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Receive/retrieve the product data that has been passed by the intent
        val name = intent.getStringExtra("product_name")
        val cost = intent.getIntExtra("product_cost", 0)
        val description = intent.getStringExtra("product_description")
        val photo = intent.getStringExtra("product_photo")

        // 2. Find views by their IDs
        val imgProduct = findViewById<ImageView>(R.id.pay_product_image)
        val txtProductName = findViewById<TextView>(R.id.pay_product_name)
        val txtProductDesc = findViewById<TextView>(R.id.pay_product_description)
        val txtProductCost = findViewById<TextView>(R.id.pay_product_price)
        val editPhone = findViewById<TextInputEditText>(R.id.phone_number)
        val btnPay = findViewById<Button>(R.id.btn_pay)
        val progressBar = findViewById<ProgressBar>(R.id.payment_progress)

        // 3. Update the views with values from the intent
        txtProductName.text = name
        txtProductDesc.text = description
        txtProductCost.text = "Ksh $cost"

        // Load image using Glide
        val imageUrl = "https://kus-kus.alwaysdata.net/static/images/$photo"
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(imgProduct)

        // 4. Set a click listener on the pay button
        btnPay.setOnClickListener {
            val api="https://kus-kus.alwaysdata.net/api/mpesa_payment"
            //data object from request params to put out data into the api
            val data= RequestParams()
            data.put("phone",editPhone.text.toString().trim())
            data.put("amount",cost) // cost from the product that was passed

            //helper object to access post function from ApiHelper
            val helper=ApiHelper(applicationContext)
            helper.post(api,data)

        }


    }
}