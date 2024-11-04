package com.cmex.lesson2shoppinglist.domain

interface WorkingSoppingList {

    fun getSoppingList():List<ShopItem>

    fun addSoppingList(shopItem: ShopItem)

    fun removeShopItemFromShoppingList(shopItem: ShopItem)

    fun  editShopItemInShoppingList(shopItem: ShopItem)

}

