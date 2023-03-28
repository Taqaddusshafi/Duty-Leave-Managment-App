package com.example.dutyleavemanagementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btn =findViewById<ImageButton>(R.id.getst)

        btn.setOnClickListener() {
            var intent = Intent(applicationContext, Login1::class.java)
            startActivity(intent)
        }

    }
}