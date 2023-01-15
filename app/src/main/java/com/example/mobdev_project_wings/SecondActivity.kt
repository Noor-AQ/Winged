package com.example.mobdev_project_wings

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat

class SecondActivity : AppCompatActivity() {
    var sum: Double = 0.0;
    var quantity:Int = -1
    var types:String = ""
    var sauces:String = ""
    var drinks:String = ""
    var COMPLETE: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val bt : Button = findViewById(R.id.bt);
        val bt2 : Button = findViewById(R.id.bt2);




        bt.setOnClickListener(){

//            val uri = contentResolver.delete(WingsProvider.CONTENT_URI,null, null)

            if(quantity == -1 || types == "" || sauces == "" || drinks == "")
                Toast.makeText(this, "COMPLETE ORDER PLEASE", Toast.LENGTH_LONG).show()
            else {
                insertTable(quantity, types, sauces, drinks)
                COMPLETE = true
            }
            if (COMPLETE == true){
                val txt : TextView = findViewById(R.id.textView10);
                sum = Math.round(sum * 1000.0) / 1000.0
                txt.text = sum.toString();
                startService();
            }
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

        types = value
        quantity = num

        val txt : TextView = findViewById(R.id.textView4);
        txt.text = num.toString() + " Wings";

        val txt2 : TextView = findViewById(R.id.textView11);
        txt2.text = value + " Wings";

        if (num != null) {
            sum = (num*type)
        };
    }

    fun receive_feedback2(value:String){
        sauces = value

        val txt : TextView = findViewById(R.id.textView7);
        txt.text = value + " Sauce";
    }

    fun receive_feedback3(value:String){
        drinks = value

        val txt : TextView = findViewById(R.id.textView8);
        txt.text = "1 " + value;
        sum+= 0.50;
    }
     fun insertTable(quantity: Int, types: String, sauces: String, drinks:String){
        var values = ContentValues()
        values.put(
            WingsProvider.QUANTITY, quantity
        )
        values.put(
            WingsProvider.TYPES, types
        )
        values.put(
            WingsProvider.SAUCES, sauces
        )
        values.put(
            WingsProvider.DRINKS, drinks
        )
        val uri = contentResolver.insert(
            WingsProvider.CONTENT_URI, values
        )
    }


}