package com.cmex.lesson2shoppinglist.presentation

import androidx.databinding.BindingAdapter
import com.cmex.lesson2shoppinglist.R
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("bindErrorName")
fun checkInputName(inputLayout: TextInputLayout,check:Boolean){
    if(check) inputLayout.error=inputLayout.context.getString(R.string.error_name)
}
@BindingAdapter("bindErrorCount")
fun checkInputCount(inputLayout: TextInputLayout,check:Boolean){
    if(check) inputLayout.error=inputLayout.context.getString(R.string.error_count)
}