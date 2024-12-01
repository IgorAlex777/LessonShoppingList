package com.cmex.lesson2shoppinglist.data.usecase

import com.cmex.lesson2shoppinglist.data.InterfaceWorkToShopList
import com.cmex.lesson2shoppinglist.domain.ShopItem

class EditShopItemUseCase(private val listener:InterfaceWorkToShopList) {
  suspend  fun editShopItemUC(shopItem: ShopItem){
      listener.editShopItem(shopItem)
    }
}