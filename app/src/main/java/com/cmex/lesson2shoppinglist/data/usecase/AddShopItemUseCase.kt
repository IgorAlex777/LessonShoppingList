package com.cmex.lesson2shoppinglist.data.usecase

import com.cmex.lesson2shoppinglist.data.InterfaceWorkToShopList
import com.cmex.lesson2shoppinglist.domain.ShopItem

class AddShopItemUseCase(private val listener:InterfaceWorkToShopList) {
    fun addShopItemUC(shopItem: ShopItem){
       listener.addShopItem(shopItem)
    }
}