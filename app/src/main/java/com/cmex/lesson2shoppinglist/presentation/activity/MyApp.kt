package com.cmex.lesson2shoppinglist.presentation.activity

import android.app.Application
import com.cmex.lesson2shoppinglist.data.di.DaggerComponentShopList

class MyApp :Application(){
    val component by lazy{
         DaggerComponentShopList.factory().create(this)
    }
    companion object{
      lateinit var  instance:MyApp
    }

    override fun onCreate() {
        super.onCreate()
        instance=this
    }
}