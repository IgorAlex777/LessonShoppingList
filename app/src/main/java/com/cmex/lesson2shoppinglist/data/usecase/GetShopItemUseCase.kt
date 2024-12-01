package com.cmex.lesson2shoppinglist.data.usecase

import androidx.lifecycle.LiveData
import com.cmex.lesson2shoppinglist.data.InterfaceWorkToShopList
import com.cmex.lesson2shoppinglist.data.myLog
import com.cmex.lesson2shoppinglist.domain.ShopItem

class GetShopItemUseCase(private val listener:InterfaceWorkToShopList) {
  suspend  fun getShopItemUC(id:Int):ShopItem{

       return listener.getShopItem(id)
    }
}