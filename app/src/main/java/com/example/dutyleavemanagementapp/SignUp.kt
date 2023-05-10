package com.example.dutyleavemanagementapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val btn1=findViewById<Button>(R.id.sgup)
        // Initialize Firebase Auth
        auth = Firebase.auth

        var btn2: TextView = findViewById(R.id.signup)
        btn2.setOnClickListener() {
            var intent = Intent(applicationContext, Login1::class.java)
            startActivity(intent)
        }
        onStart()

        btn1.setOnClickListener {

            performsignup()
        }
    }
    private  fun performsignup(){
        var progressbar = findViewById<ProgressBar>(R.id.progressBar)
        val email= findViewById<EditText>(R.id.semail)
        val name= findViewById<EditText>(R.id.name11)
        val pass= findViewById<EditText>(R.id.spassword)
        val  inputemail=email.text.toString()
        val  inputname=name.text.toString()
        val  inputpass=pass.text.toString()
        if (email.text.isEmpty()||pass.text.isEmpty()||pass.text.isEmpty()){
            Toast.makeText(baseContext,"Name,Email or Password cant be empty",Toast.LENGTH_SHORT).show()
            return
        }
        progressbar.setVisibility(View.VISIBLE);
        auth.createUserWithEmailAndPassword(inputemail, inputpass,)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    progressbar.setVisibility(View.VISIBLE);

                    val user = FirebaseAuth.getInstance().currentUser
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(inputname)
                        .build()
                    user?.updateProfile(profileUpdates)
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(baseContext,"Sucess",Toast.LENGTH_LONG).show()
                            }
                        }
                    // User signed up successfully

                    var intent = Intent(applicationContext, MainActivity3::class.java)
                    startActivity(intent)

                } else {
                    progressbar.setVisibility(View.GONE);
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext,"Some thing went wrong please try gain",Toast.LENGTH_LONG).show()

                }
            }
            .addOnFailureListener{
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

}