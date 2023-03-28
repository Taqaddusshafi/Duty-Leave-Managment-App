package com.example.dutyleavemanagementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity7 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)
        val username=findViewById<EditText>(R.id.Username)
        val password=findViewById<EditText>(R.id.Password)
        val adminlogin=findViewById<Button>(R.id.adminbtn)
        val a=username.text.toString()
        val b=password.text.toString()

        adminlogin.setOnClickListener(){




        }

    }
}