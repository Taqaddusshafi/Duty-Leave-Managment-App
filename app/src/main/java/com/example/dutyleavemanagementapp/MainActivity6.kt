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
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity6 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)
        var viewPager = findViewById(R.id.viewPager) as ViewPager
        var tablayout = findViewById(R.id.tablayout) as TabLayout
        val toolbar = findViewById(R.id.toolbar60) as androidx.appcompat.widget.Toolbar?
        setSupportActionBar(toolbar)
        val fragmentAdapter = fragmentadapter(supportFragmentManager)
        fragmentAdapter.addFragment(pending(),"PENDING")
        fragmentAdapter.addFragment(approved(),"APPROVED")
        fragmentAdapter.addFragment(rejected(),"REJECTED")
        fragmentAdapter.addFragment(all(),"ALL")

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(androidx.appcompat.R.drawable.abc_ic_ab_back_material)



        val title = "Status"
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



        viewPager.adapter = fragmentAdapter
        tablayout.setupWithViewPager(viewPager)
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