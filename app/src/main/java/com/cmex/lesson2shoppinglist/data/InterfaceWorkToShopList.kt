package com.cmex.lesson2shoppinglist.data

import androidx.lifecycle.LiveData
import com.cmex.lesson2shoppinglist.domain.ShopItem

interface InterfaceWorkToShopList {
    fun getShoppingList():LiveData<List<ShopItem>>
    fun getShopItem(id:Int):ShopItem
    fun removeShopItem(shopItem: ShopItem)
    fun editShopItem(shopItem: ShopItem)
    fun addShopItem(shopItem: ShopItem)
}