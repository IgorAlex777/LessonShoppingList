package com.cmex.lesson2shoppinglist.domain

interface WorkingSoppingList {

    fun getSoppingList():List<ShopItem>

    fun addSoppingList(shopItem: ShopItem)

    fun removeShopItemFromShoppingList(shopItem: ShopItem)

    fun  editShopItem(shopItem: ShopItem)

    fun getShopItem(id:Int):ShopItem

}

