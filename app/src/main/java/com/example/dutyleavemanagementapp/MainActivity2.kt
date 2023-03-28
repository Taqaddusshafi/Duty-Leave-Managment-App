package com.example.dutyleavemanagementapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.*
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.HashMap

class MainActivity2 : AppCompatActivity() {
     var a:String=""
     var b:String=""
    private var db:FirebaseFirestore = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val toolbar = findViewById(R.id.toolbar20) as androidx.appcompat.widget.Toolbar?
        setSupportActionBar(toolbar)
        var progressbar = findViewById<ProgressBar>(R.id.progressBar1)
        var sb =findViewById<Button>(R.id.submit)
        var name=findViewById<EditText>(R.id.name)
        var time1=findViewById<EditText>(R.id.time)
        var dat1=findViewById<EditText>(R.id.date)
        var semninar=findViewById<EditText>(R.id.seminarcode)
        var Regno=findViewById<EditText>(R.id.Regno)
        var rollno=findViewById<EditText>(R.id.rollno)
        var section=findViewById<EditText>(R.id.section)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(androidx.appcompat.R.drawable.abc_ic_ab_back_material)


        time1.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
                // Do something with the selected time
                val time = String.format("%02d:%02d", selectedHour, selectedMinute)
                time1.setText(time)
                b = time1.text.toString()
            }, hour, minute, DateFormat.is24HourFormat(this))
            timePickerDialog.show()
        }

        dat1.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                // Do something with the selected date
                val date = String.format("%02d/%02d/%04d", selectedDayOfMonth, selectedMonth + 1, selectedYear)
                dat1.setText(date)
                a=dat1.text.toString()
            }, year, month, dayOfMonth)
            datePickerDialog.show()
        }



        val title = "Form"
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
        val nm=name.text
        val sm=semninar.text

        val sc=section.text




        val userId = FirebaseAuth.getInstance().currentUser?.uid

        sb.setOnClickListener(){
            val rl=rollno.text.toString()
            val rg=Regno.text.toString()
            val dat2=a
            val time2=b

            if (nm.isEmpty() || sm.isEmpty() || rl.isEmpty() || rg.isEmpty() || sc.isEmpty() || dat2.isEmpty() || time2.isEmpty()){
                Toast.makeText(this,"All fields are mandatory",Toast.LENGTH_SHORT).show()
            }
            else{
            progressbar.setVisibility(View.VISIBLE);
            val add = HashMap<String,Any>()
            add["userId"]="$userId"
            add["Name"] = "$nm"
            add["time"] = "$time2"
            add["date"] = "$dat2"
            add["Seminar_Name"] = "$sm"
            add["Roll_No"] = "$rl"
            add["Section"] = "$sc"
            add["Reg_No"] = "$rg"
            add["Status"] ="pending"
            db.collection("users1")
                .add(add)
                .addOnSuccessListener {
                    progressbar.setVisibility(View.GONE);
                     val intent = Intent(applicationContext,MainActivity5::class.java)
                     startActivity(intent)
                }
                .addOnFailureListener {
                    progressbar.setVisibility(View.GONE);
                    Toast.makeText(this," Error Please Try Again ",Toast.LENGTH_LONG).show()
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
            R.id.Home->{val intent =Intent(applicationContext,MainActivity3::class.java)
                startActivity(intent)
            }

        }
        return super.onOptionsItemSelected(item)
    }
}