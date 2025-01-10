package com.cmex.lesson2shoppinglist.data.usecase

import com.cmex.lesson2shoppinglist.data.InterfaceWorkToShopList
import com.cmex.lesson2shoppinglist.domain.ShopItem
import javax.inject.Inject

class AddShopItemUseCase @Inject constructor(private val listener:InterfaceWorkToShopList) {
  suspend  fun addShopItemUC(shopItem: ShopItem){
       listener.addShopItem(shopItem)
    }
}