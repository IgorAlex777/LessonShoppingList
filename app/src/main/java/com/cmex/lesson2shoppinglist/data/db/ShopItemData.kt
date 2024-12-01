package com.cmex.lesson2shoppinglist.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_item")
data class ShopItemData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val count: Int,
    val active: Boolean
)
