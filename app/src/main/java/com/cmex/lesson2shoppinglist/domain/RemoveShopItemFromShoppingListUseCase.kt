package com.cmex.lesson2shoppinglist.domain

class RemoveShopItemFromShoppingListUseCase(private val listener:WorkingSoppingList) {
    fun removeShopItemFromShopList(shopItem: ShopItem){
        listener.removeShopItemFromShoppingList(shopItem)
    }
}