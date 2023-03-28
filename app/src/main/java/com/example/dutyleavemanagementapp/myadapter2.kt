package com.example.dutyleavemanagementapp

import android.content.Context
import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Myadapter1(private val userList1: MutableList<userdata2>) : RecyclerView.Adapter<Myadapter1.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myadapter1.MyViewHolder {
        val itemView1 = LayoutInflater.from(parent.context).inflate(R.layout.list_items2, parent, false)
        return MyViewHolder(itemView1)
    }

    override fun onBindViewHolder(holder: Myadapter1.MyViewHolder, position: Int) {
        val user: userdata2 = userList1[position]
        holder.seminarname.text = user.Seminar_Name
        holder.seminartime.text = user.time
        holder.seminardate.text = user.date
        holder.seminarstat.text = user.Status
        holder.name1.text = user.Name
        holder.rolno.text = user.Roll_No
        holder.regno.text = user.Reg_No
        holder.sction.text = user.Section

        holder.st.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Status changed to approved", Toast.LENGTH_SHORT).show()
            val a=user.Seminar_Name



            val db = FirebaseFirestore.getInstance()
            val user = FirebaseAuth.getInstance().currentUser
            val query = db.collection("users1").whereEqualTo("Seminar_Name",a)

            query.get().addOnSuccessListener { documents ->
                for (document in documents) {
                    db.collection("users1").document(document.id)
                        .update("Status", "approved")
                        .addOnSuccessListener {
                            Log.d("Myadapter1", "Status changed to approved")
                            holder.seminarstat.text = "approved"
                        }
                        .addOnFailureListener {
                            Log.e("Myadapter1", "Failed to update status", it)
                        }
                }
            }.addOnFailureListener {
                Log.e("Myadapter1", "Failed to query database", it)
            }
        }
        holder.rj.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Status changed to approved", Toast.LENGTH_SHORT).show()
            val a=user.Seminar_Name



            val db = FirebaseFirestore.getInstance()
            val user = FirebaseAuth.getInstance().currentUser
            val query = db.collection("users1").whereEqualTo("Seminar_Name",a)

            query.get().addOnSuccessListener { documents ->
                for (document in documents) {
                    db.collection("users1").document(document.id)
                        .update("Status", "rejected")
                        .addOnSuccessListener {
                            Log.d("Myadapter1", "Status changed to approved")
                            holder.seminarstat.text = "rejected"
                        }
                        .addOnFailureListener {
                            Log.e("Myadapter1", "Failed to update status", it)
                        }
                }
            }.addOnFailureListener {
                Log.e("Myadapter1", "Failed to query database", it)
            }
        }
    }

    override fun getItemCount(): Int {
        return userList1.size
    }

    public class MyViewHolder(itemView1: View) : RecyclerView.ViewHolder(itemView1) {
        val seminarname: TextView = itemView1.findViewById(R.id.sminarname)
        val seminartime: TextView = itemView1.findViewById(R.id.sminartime)
        val seminardate: TextView = itemView1.findViewById(R.id.seminardate)
        val seminarstat: TextView = itemView1.findViewById(R.id.seminarstatus)
        val name1: TextView = itemView1.findViewById(R.id.namefr)
        val regno: TextView = itemView1.findViewById(R.id.Regno11)
        val rolno: TextView = itemView1.findViewById(R.id.Rollno111)
        val sction: TextView = itemView1.findViewById(R.id.section11)
        val st: Button = itemView1.findViewById(R.id.approve)
        val rj: Button = itemView1.findViewById(R.id.reject)

    }
}
