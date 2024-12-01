package com.cmex.lesson2shoppinglist.data

import androidx.lifecycle.LiveData
import com.cmex.lesson2shoppinglist.data.db.ShopItemData
import com.cmex.lesson2shoppinglist.domain.ShopItem

interface InterfaceWorkToShopList {
   fun getShoppingList():LiveData<List<ShopItem>>
suspend    fun getShopItem(id:Int):ShopItem
 suspend   fun removeShopItem(shopItem: ShopItem)
  suspend  fun editShopItem(shopItem: ShopItem)
   suspend fun addShopItem(shopItem: ShopItem)
}