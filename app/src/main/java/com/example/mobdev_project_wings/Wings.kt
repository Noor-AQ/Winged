package com.example.mobdev_project_wings


import android.content.ContentProvider
import android.content.Intent
import android.content.ContentResolver
import android.content.ContentValues
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.insert
import android.view.View
import androidx.fragment.app.DialogFragment
import android.widget.*


class Wings : DialogFragment(R.layout.wings) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var flag = "Traditional";
        var options = arrayOf("Traditional","Boneless","Breaded");
        val spin : Spinner = view.findViewById(R.id.spinnerV);
        spin.adapter = getActivity()?.let { ArrayAdapter<String>(it.getApplicationContext(),android.R.layout.simple_list_item_1,options) };



        var num: Double = 0.23;
        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                flag = options.get(p2)
                //every time spinner selection is changed we change price
                if (flag=="Traditional") num = 0.23;
                else if (flag=="Boneless") num = 0.27;
                else num = 0.30;
            } //p2 is the index of selected item    }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")    }
        }

//        val edit : EditText = view.findViewById(R.id.editTextNumber);
        val canc : Button = view.findViewById(R.id.cancelBT1);
        canc.setOnClickListener(){
            dismiss();
        }

        val bt : Button = view.findViewById(R.id.submitBT2);


        //submit button
        bt.setOnClickListener{
            val edit : EditText = view.findViewById(R.id.editTextNumber);
            val m1: SecondActivity = getActivity() as SecondActivity;
            m1.receive_feedback1(flag,edit.text.toString().toInt(),num);
//            m1.insertTable(edit.text.toString().toInt(), flag,"", "")
            dismiss();
        }

    }




}