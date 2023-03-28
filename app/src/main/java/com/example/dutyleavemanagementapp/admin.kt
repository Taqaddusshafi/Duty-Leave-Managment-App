package com.example.dutyleavemanagementapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*

class admin : AppCompatActivity() {
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: MutableList<userdata2>
    private lateinit var userAdapter: Myadapter1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        recyclerView=findViewById(R.id.reccycle22)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        userArrayList= mutableListOf()
        userAdapter=Myadapter1(userArrayList)
        recyclerView.adapter=userAdapter
        EventChangelistener()

    }
    private  fun EventChangelistener(){

        val usersRef = db.collection("users1")
        usersRef.whereEqualTo("Status","pending").addSnapshotListener(object :
            EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if(error!=null){
                    Log.e("firestore error",error.message.toString())
                    return
                }
                for (dc: DocumentChange in value?.documentChanges!!){
                    if (dc.type== DocumentChange.Type.ADDED){
                        userArrayList.add(dc.document.toObject(userdata2::class.java))
                    }
                }
                userAdapter.notifyDataSetChanged()
            }

        })
    }
}