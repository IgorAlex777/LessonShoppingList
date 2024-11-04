package com.cmex.lesson2shoppinglist.data

import android.annotation.SuppressLint
import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date

fun myLog(text:String){
     Log.d("CMEX",text).toString()
}

@SuppressLint("SimpleDateFormat")
fun utilDate(time:Long):String{
    val date=Date(time)
    return SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(date)
}

@SuppressLint("SimpleDateFormat")
fun utilTextDateToDate(dateText:String,pattern:String):Long{
    if(pattern.length==dateText.length){
    val date=SimpleDateFormat(pattern).parse(dateText)
    date?.let {
        return date.time
    }
    }
   return 0
}