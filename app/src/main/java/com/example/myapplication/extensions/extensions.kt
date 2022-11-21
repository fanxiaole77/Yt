package com.example.myapplication.extensions

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.example.myapplication.SmartCiryApplication
import javax.xml.datatype.Duration

fun CharSequence.showToast(duration:Int = Toast.LENGTH_SHORT){
    Toast.makeText(SmartCiryApplication.context,this,duration).show()
}

fun SharedPreferences.edit(action:SharedPreferences.Editor.() ->Unit){
    val editor = edit()
    action(editor)
    editor.apply()
}

val sharedPreferences:SharedPreferences = SmartCiryApplication.context.getSharedPreferences(
    "SmartCityPreferences",
    Context.MODE_PRIVATE
)