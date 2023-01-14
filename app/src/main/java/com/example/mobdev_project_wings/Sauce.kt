package com.example.mobdev_project_wings

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment

class Sauce: DialogFragment(R.layout.sauce) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        val bt : Button = view.findViewById(R.id.submitBT2);
        val radioGroupY = view.findViewById<RadioGroup>(R.id.SauceRadio);
        val cancel:Button = view.findViewById(R.id.cancelBT);
        cancel.setOnClickListener(){
            dismiss();
        }
        bt.setOnClickListener(){
            val selectedOption: Int = radioGroupY.checkedRadioButtonId;
            val radioButton2 = view.findViewById<RadioButton>(selectedOption);
            val m1: SecondActivity = getActivity() as SecondActivity;
            m1.receive_feedback2(radioButton2.text.toString());
            dismiss();
        }
    }
}