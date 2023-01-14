package com.example.mobdev_project_wings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val order:Button= findViewById(R.id.orderBtn)

        order.setOnClickListener{
            val intent= Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

    }
}