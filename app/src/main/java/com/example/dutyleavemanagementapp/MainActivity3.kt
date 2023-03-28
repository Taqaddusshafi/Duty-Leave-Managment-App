package com.example.dutyleavemanagementapp

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity3 : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val toolbar = findViewById(R.id.toolbar3) as androidx.appcompat.widget.Toolbar?
        setSupportActionBar(toolbar)

        onBackPressed()



        val navView = findViewById<NavigationView>(R.id.nav_view)
        val headerLayout = navView.getHeaderView(0)
        var drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val imageView = headerLayout.findViewById<ImageView>(R.id.imageView)
        imageView.setImageResource(R.drawable.img_logo)
        toggle = ActionBarDrawerToggle(this, drawerLayout,R.string.nav_open, R.string.nav_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.menuiconsm)


        val title = "Dashboard "
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
                    setPadding(-50, paddingTop, 50, paddingBottom)
                }
            }
        }
        val user = FirebaseAuth.getInstance().currentUser
        if (user?.displayName != null) {
            val displayName = user?.displayName
            val nameTextView = headerLayout.findViewById<TextView>(R.id.nameTextView)
            nameTextView.text =displayName
        } else {
            val nameTextView = headerLayout.findViewById<TextView>(R.id.nameTextView)
            val displayName ="Name not available"
            nameTextView.text =displayName

        }

        val userId = FirebaseAuth.getInstance().currentUser?.uid

        if (userId == "xL54ZYP4nifgS4F4SmU0jheQdaD2") {
            val navView: NavigationView = findViewById(R.id.nav_view)
            val menu = navView.menu
            val option = menu.findItem(R.id.five)
            option.isVisible = true
        }
        //val inflater = LayoutInflater.from(this)
       // val footerView = layoutInflater.inflate(R.layout.bottom_nav, null)
       // navView.addFooterView(footerView)

        // Set the user name

        navView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item click
            when (menuItem.itemId) {
                R.id.firstItem-> {
                    // Do something
                    var intent = Intent(applicationContext, MainActivity4::class.java)
                    startActivity(intent)
                }
                R.id.secondtItem -> {
                    val recipient = "taqaddusabc@gmail.com"
                    openEmailClient(recipient)

                }
                R.id.thirdItem -> {
                    var intent = Intent(applicationContext, aboutpage::class.java)
                    startActivity(intent)

                }
                R.id.frth -> {
                    val recipient = "taqaddusabc@gmail.com"
                    openEmailClient(recipient)
                }
                R.id.fourth -> {
                    // Do something else
                    FirebaseAuth.getInstance().signOut();
                    var intent = Intent(applicationContext, Login1::class.java)
                    startActivity(intent)
                }
                R.id.five -> {
                    // Do something else

                    var intent = Intent(applicationContext, admin::class.java)
                    startActivity(intent)
                }
                // Add more menu items here
            }

            // Close the Navigation Drawer after the item is clicked
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }



        var btn = findViewById<ImageButton>(R.id.getst123)
        var btn2 = findViewById<ImageButton>(R.id.getst1234)

        btn.setOnClickListener(){
            var intent = Intent(applicationContext, MainActivity2::class.java)
            startActivity(intent)
        }
        btn2.setOnClickListener(){
            var intent = Intent(applicationContext, MainActivity6::class.java)
            startActivity(intent)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menudash, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            // Open the navigation drawer
            val drawerLayout=findViewById<DrawerLayout>(R.id.drawer_layout)
            drawerLayout.openDrawer(GravityCompat.START)
            return true
        }
        when (item.itemId) {
            R.id.Home->{val intent =Intent(applicationContext,MainActivity3::class.java)
                startActivity(intent)
            }

        }
        return super.onOptionsItemSelected(item)
    }
    fun openEmailClient(recipient: String) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:$recipient")
        startActivity(intent)
    }




    override fun onBackPressed() {

    }
}
