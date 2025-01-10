package com.cmex.lesson2shoppinglist.presentation

import android.app.Application

class AppShopList:Application() {
    companion object{
        lateinit var instance : AppShopList
    }

    override fun onCreate(){
        super.onCreate()
        instance = this
    }
}