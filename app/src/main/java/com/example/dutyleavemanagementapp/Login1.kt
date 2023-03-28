package com.example.dutyleavemanagementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Login1 : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login1)
        var progressbar=findViewById<ProgressBar>(R.id.progressBar)


        progressbar.setVisibility(View.GONE);

        auth = Firebase.auth
        onStart()

        var btn2: TextView = findViewById(R.id.login)
        btn2.setOnClickListener() {
            var intent = Intent(applicationContext, SignUp::class.java)
            startActivity(intent)
        }

        var btn1: TextView = findViewById(R.id.lg1)


        btn1.setOnClickListener {
            perfomLogin()
        }
        onBackPressed()
    }
    private fun  perfomLogin(){
        val email= findViewById<EditText>(R.id.lemail)
        val pass= findViewById<EditText>(R.id.lpass)
        val  inputemail=email.text.toString()
        val  inputpass=pass.text.toString()
        var progressbar = findViewById<ProgressBar>(R.id.progressBar)
        if (email.text.isEmpty()||pass.text.isEmpty()){
            Toast.makeText(baseContext,"Email or Password cant be empty", Toast.LENGTH_SHORT).show()
            return
        }
        progressbar.setVisibility(View.VISIBLE);
        auth.signInWithEmailAndPassword(inputemail, inputpass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    progressbar.setVisibility(View.GONE);
                    var intent = Intent(applicationContext, MainActivity3::class.java)
                    startActivity(intent)

                } else {
                    progressbar.setVisibility(View.GONE);


                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }
            .addOnFailureListener{
                progressbar.setVisibility(View.GONE);
                Toast.makeText(this,"Some thing went wrong please try gain${it.localizedMessage}",Toast.LENGTH_LONG).show()
            }


    }
    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            var intent = Intent(applicationContext, MainActivity3::class.java)
            startActivity(intent)
        }
    }
    override fun onBackPressed() {

    }
}


