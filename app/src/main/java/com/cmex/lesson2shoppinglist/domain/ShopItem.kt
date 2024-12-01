package com.cmex.lesson2shoppinglist.domain

data class ShopItem (
    val name:String,
    val count:Int,
    val active:Boolean,
    var id:Int=0
)
