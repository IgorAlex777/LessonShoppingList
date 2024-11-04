package com.cmex.lesson2shoppinglist.domain

class GetShoppingListUseCase(private val listener:WorkingSoppingList) {
    fun getShopList():List<ShopItem>{
     return listener.getSoppingList()
    }
}