package com.example.dutyleavemanagementapp

import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView


class Myadapter(private val userList: ArrayList<userdata>) :RecyclerView.Adapter<Myadapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent:ViewGroup,viewType: Int):Myadapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: Myadapter.MyViewHolder,position: Int) {
        val user :userdata=userList[position]
        holder.seminarname.text=user.Seminar_Name
        holder.seminartime.text=user.time
        holder.seminardate.text=user.date
        holder.seminarstat.text=user.Status
    }
    override fun getItemCount(): Int {
        return userList.size
    }
    public class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val seminarname:TextView=itemView.findViewById(R.id.sminarname)
        val seminartime:TextView=itemView.findViewById(R.id.sminartime)
        val seminardate:TextView=itemView.findViewById(R.id.seminardate)
        val seminarstat:TextView=itemView.findViewById(R.id.seminarstatus)

    }
}
