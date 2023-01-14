package com.example.mobdev_project_wings


import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment

class Drinks:DialogFragment(R.layout.drinks) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        val radioGroupx = view.findViewById<RadioGroup>(R.id.DrinksRadio);
        val cancel : Button = view.findViewById(R.id.cancel);
        val submit : Button = view.findViewById(R.id.submit);
        cancel.setOnClickListener(){
            dismiss();
        }

        submit.setOnClickListener(){
            val selectedOption: Int = radioGroupx.checkedRadioButtonId;
            val radioButton2 = view.findViewById<RadioButton>(selectedOption);
            val m1: SecondActivity = getActivity() as SecondActivity;
            m1.receive_feedback3(radioButton2.text.toString());
            dismiss();
        }
    }
}