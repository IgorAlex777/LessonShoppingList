package com.cmex.lesson2shoppinglist.domain

import java.io.Serializable

data class ShopItem(
    val id:Int,
    val name:String,
    val count:Int,
    val active:Boolean,
    val imageUri:String
):Serializable
