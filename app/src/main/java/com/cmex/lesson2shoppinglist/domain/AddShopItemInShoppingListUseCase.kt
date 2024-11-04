package com.cmex.lesson2shoppinglist.domain

class AddShopItemInShoppingListUseCase(private val listener:WorkingSoppingList) {
    fun addShopItemInShopList(shopItem: ShopItem){
        listener.addSoppingList(shopItem)
    }
}