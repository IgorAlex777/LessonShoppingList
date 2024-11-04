package com.cmex.lesson2shoppinglist.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity(tableName = "Shopping_List")
data class ShopItem(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name = "name")
    val name:String,
    @ColumnInfo(name="count")
    val count:Int,
    @ColumnInfo(name="active")
    val active:Boolean,
    @ColumnInfo(name="image")
    val imageUri:String
):Serializable
