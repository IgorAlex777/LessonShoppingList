package com.cmex.lesson2shoppinglist.data.usecase

import com.cmex.lesson2shoppinglist.data.InterfaceWorkToShopList
import com.cmex.lesson2shoppinglist.domain.ShopItem

class RemoveShopItemUseCase(private val listener: InterfaceWorkToShopList) {
    fun removeShopItemUC(shopItem: ShopItem){
        listener.removeShopItem(shopItem)
    }
}