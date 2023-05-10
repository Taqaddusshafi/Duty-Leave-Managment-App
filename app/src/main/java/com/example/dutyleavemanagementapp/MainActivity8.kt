package com.example.dutyleavemanagementapp
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.dutyleavemanagementapp.databinding.ActivityMain8Binding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class MainActivity8: AppCompatActivity() {

    private lateinit var binding: ActivityMain8Binding
    private lateinit var storageRef: StorageReference
    private lateinit var firebaseFirestore: FirebaseFirestore
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain8Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar21
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(androidx.appcompat.R.drawable.abc_ic_ab_back_material)

        val title = "Admin Dashboard"
        toolbar?.title = title

        if (toolbar != null) {
            toolbar.setTitleTextColor(Color.WHITE)
        }
        toolbar?.getChildAt(0).let { view ->
            if (view is TextView) {
                view.apply {
                    gravity = Gravity.CENTER_HORIZONTAL
                    layoutParams = layoutParams.apply {
                        width = ViewGroup.LayoutParams.MATCH_PARENT
                    }
                    setPadding(-200, paddingTop, 0, paddingBottom)
                }
            }
        }

        initVars()
        registerClickEvents()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menudash, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        when (item.itemId) {
            R.id.Home->{val intent = Intent(applicationContext,MainActivity3::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun registerClickEvents() {
        binding.submit.setOnClickListener {
            uploadImage()
        }



        binding.imgset.setOnClickListener {
            resultLauncher.launch("image/*")
        }
    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) {

        imageUri = it
        binding.imgset.setImageURI(it)
    }


    private fun initVars() {

        storageRef = FirebaseStorage.getInstance().reference.child("Images")
        firebaseFirestore = FirebaseFirestore.getInstance()
    }

    private fun uploadImage() {
        val image = imageUri
        val text = binding.link.text.toString().trim()
            val text1 = binding.link.text.toString()

        if (image == null || text.isEmpty()) {
            Toast.makeText(this, "All fields are mandatory", Toast.LENGTH_SHORT).show()
            return
        }
        else if (!Patterns.WEB_URL.matcher(text1).matches()){
                Toast.makeText(this, "URL is not valid", Toast.LENGTH_SHORT).show()
            }
        else {
            binding.progressBar2.visibility = View.VISIBLE
            storageRef = storageRef.child(System.currentTimeMillis().toString())
            imageUri?.let {
                storageRef.putFile(it).addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        storageRef.downloadUrl.addOnSuccessListener { uri ->

                            val map = HashMap<String, Any>()
                            map["pic"] = uri.toString()
                            map["text"] = binding.link.text.toString()

                            firebaseFirestore.collection("images").add(map)
                                .addOnCompleteListener { firestoreTask ->

                                    if (firestoreTask.isSuccessful) {
                                        Toast.makeText(
                                            this,
                                            "Uploaded Successfully",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                    } else {
                                        Toast.makeText(
                                            this,
                                            firestoreTask.exception?.message,
                                            Toast.LENGTH_SHORT
                                        ).show()

                                    }
                                    binding.progressBar2.visibility = View.GONE
                                    binding.imgset.setImageResource(R.drawable.vector2)
                                    binding.link.text=null

                                }
                        }
                    } else {
                        Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                        binding.progressBar2.visibility = View.GONE
                        binding.imgset.setImageResource(R.drawable.vector2)
                        binding.link.text=null
                    }
                }
            }
        }
    }

}
