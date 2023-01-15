package com.example.mobdev_project_wings

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val uri = contentResolver.delete(WingsProvider.CONTENT_URI,null, null)
        val order:Button= findViewById(R.id.orderBtn)
        val location1:Button = findViewById(R.id.locationBtn);
        order.setOnClickListener{
            val intent= Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
        location1.setOnClickListener() {
            val mapIntent: Intent = Uri.parse(
                "https://goo.gl/maps/CRgKSqhb4hdn3jeWA"
            ).let { location ->
                // Or map point based on latitude/longitude
                Intent(Intent.ACTION_VIEW, location)
            }
            startActivity(mapIntent);
        }
        // please work
    }

}
