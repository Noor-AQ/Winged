package com.example.mobdev_project_wings

import android.content.ContentValues
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
//        var values = ContentValues()
//        values.put(
//            WingsProvider.QUANTITY, 5
//        )
//        values.put(
//            WingsProvider.TYPE, "Wing"
//        )
//        values.put(
//            WingsProvider.SAUCE, "BBQ"
//        )
//        values.put(
//            WingsProvider.DRINK, "water"
//        )
//        val uri = contentResolver.insert(
//            WingsProvider.CONTENT_URI, values
//        )

    }
    public fun insertInitial(){

    }
}
