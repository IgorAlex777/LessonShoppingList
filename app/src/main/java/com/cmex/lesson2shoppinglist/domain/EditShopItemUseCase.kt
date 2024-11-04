package com.cmex.lesson2shoppinglist.domain

class EditShopItemUseCase (private val listener:WorkingSoppingList){
    fun editShopItem(shopItem: ShopItem){
        listener.editShopItem(shopItem)
    }
}