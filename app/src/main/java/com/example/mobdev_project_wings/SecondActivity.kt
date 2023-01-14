package com.example.mobdev_project_wings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.content.Intent
import androidx.core.content.ContextCompat

class SecondActivity : AppCompatActivity() {
    var sum: Double = 0.0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val bt : Button = findViewById(R.id.bt);
        val bt2 : Button = findViewById(R.id.bt2);

        bt.setOnClickListener(){
            val txt : TextView = findViewById(R.id.textView10);
            sum = Math.round(sum * 1000.0) / 1000.0
            txt.text = sum.toString();
             startService();
        }

        bt2.setOnClickListener(View.OnClickListener { stopService() })

    }
    fun startService() {
        val serviceIntent = Intent(this, Service::class.java)
        serviceIntent.putExtra("inputExtra", "Total is = " +sum)
        ContextCompat.startForegroundService(this, serviceIntent)
    }

    fun stopService() {
        val serviceIntent = Intent(this, Service::class.java)
        stopService(serviceIntent)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.first_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item1 ->{
                var frag1 = Wings();
                frag1.show(supportFragmentManager,"Wings Dialog");
            }
            R.id.item2 ->{
                var frag2 = Sauce();
                frag2.show(supportFragmentManager,"Sauce Dialog");
            }
            R.id.item3 ->{
                var frag3 = Drinks();
                frag3.show(supportFragmentManager,"Drinks Dialog");
            }
        }
        return true;}


    fun receive_feedback1(value:String,num:Int,type:Double) {
        val txt : TextView = findViewById(R.id.textView4);
        txt.text = num.toString() + " Wings";

        val txt2 : TextView = findViewById(R.id.textView11);
        txt2.text = value + " Wings";

        sum = (num*type);
    }

    fun receive_feedback2(value:String){
        val txt : TextView = findViewById(R.id.textView7);
        txt.text = value + " Sauce";
    }

    fun receive_feedback3(value:String){
        val txt : TextView = findViewById(R.id.textView8);
        txt.text = "1 " + value;
        sum+= 0.50;
    }




}