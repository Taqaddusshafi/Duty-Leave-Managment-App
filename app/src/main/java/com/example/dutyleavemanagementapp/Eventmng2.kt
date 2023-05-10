package com.example.dutyleavemanagementapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.TextView
import com.example.dutyleavemanagementapp.databinding.ActivityAdminBinding
import com.example.dutyleavemanagementapp.databinding.ActivityAdminManagmentBinding
import com.example.dutyleavemanagementapp.databinding.ActivityEventmng2Binding
import com.example.dutyleavemanagementapp.databinding.ActivityMain8Binding

class Eventmng2 : AppCompatActivity() {
    private lateinit var binding: ActivityEventmng2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityEventmng2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        inn1()
        val toolbar = binding.toolbar22
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
    private fun inn1(){
        binding.btnnew.setOnClickListener(){
            val intent=Intent(this,MainActivity8::class.java)
            startActivity(intent)
        }
        binding.btnold.setOnClickListener(){
            val intent=Intent(this,Deleteevent::class.java)
            startActivity(intent)
        }
    }
}