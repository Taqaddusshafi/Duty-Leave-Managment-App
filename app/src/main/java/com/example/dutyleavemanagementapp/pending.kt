package com.example.dutyleavemanagementapp

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*


class pending : Fragment() {
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<userdata>
    private lateinit var userAdapter: Myadapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pending, container, false)
        recyclerView = view.findViewById<RecyclerView>(R.id.reccycle1)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        userArrayList= arrayListOf()
        userAdapter=Myadapter(userArrayList)
        recyclerView.adapter=userAdapter
        EventChangelistener()

        return view


    }
    private  fun EventChangelistener(){
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val usersRef = db.collection("users1")
        val status ="pending"
        usersRef.whereEqualTo("userId",userId).whereEqualTo("Status",status).addSnapshotListener(object :
            EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if(error!=null){
                    Log.e("firestore error",error.message.toString())
                    return
                }
                for (dc: DocumentChange in value?.documentChanges!!){
                    if (dc.type== DocumentChange.Type.ADDED){
                        userArrayList.add(dc.document.toObject(userdata::class.java))
                    }
                }
                userAdapter.notifyDataSetChanged()
            }

        })
    }



}