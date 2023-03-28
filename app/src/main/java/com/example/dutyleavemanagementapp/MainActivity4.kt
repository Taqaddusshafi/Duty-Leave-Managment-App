package com.example.dutyleavemanagementapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.auth.User

class MainActivity4 : AppCompatActivity() {
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<userdata>
    private lateinit var userAdapter: Myadapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        val toolbar = findViewById(R.id.toolbar22) as androidx.appcompat.widget.Toolbar?
        setSupportActionBar(toolbar)
        recyclerView=findViewById(R.id.reccycle)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(androidx.appcompat.R.drawable.abc_ic_ab_back_material)

        userArrayList= arrayListOf()
        userAdapter=Myadapter(userArrayList)
        recyclerView.adapter=userAdapter
        EventChangelistener()
        val title = "Pending Duty Leaves"
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
    }
    private  fun EventChangelistener(){
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val usersRef = db.collection("users1")
        usersRef.whereEqualTo("userId",userId).whereEqualTo("Status","pending").addSnapshotListener(object :EventListener<QuerySnapshot>{
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if(error!=null){
                    Log.e("firestore error",error.message.toString())
                    return
                }
                for (dc:DocumentChange in value?.documentChanges!!){
                    if (dc.type==DocumentChange.Type.ADDED){
                        userArrayList.add(dc.document.toObject(userdata::class.java))
                    }
                }
                userAdapter.notifyDataSetChanged()
            }

        })
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
}