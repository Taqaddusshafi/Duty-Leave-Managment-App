package com.example.dutyleavemanagementapp

import android.content.Intent
import android.net.Uri

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dutyleavemanagementapp.databinding.EachItemBinding
import com.squareup.picasso.Picasso

class imageadapter(private var mList: List<String>) :
    RecyclerView.Adapter<imageadapter.ImagesViewHolder>() {

    inner class ImagesViewHolder(var binding: EachItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding = EachItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ImagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        with(holder.binding){
            val item = mList[position]
            val parts = item.split("|")
            val imageUrl = parts[0]
            val imageLink = parts[1]
            Picasso.get().load(imageUrl).into(imageView)
            imageView.setOnClickListener {
                // Open the link in a web browser
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(imageLink)
                root.context.startActivity(intent)
            }
        }
    }


    override fun getItemCount(): Int {
        return mList.size
    }
}