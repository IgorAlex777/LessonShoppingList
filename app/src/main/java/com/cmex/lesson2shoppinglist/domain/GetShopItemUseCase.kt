package com.cmex.lesson2shoppinglist.domain

class GetShopItemUseCase(private val listener:WorkingSoppingList) {
    fun getShopItem(id:Int):ShopItem{
      return listener.getShopItem(id)
    }
}