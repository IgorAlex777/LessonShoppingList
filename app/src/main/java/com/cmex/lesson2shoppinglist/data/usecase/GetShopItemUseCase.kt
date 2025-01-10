package com.cmex.lesson2shoppinglist.data.usecase

import com.cmex.lesson2shoppinglist.data.InterfaceWorkToShopList
import com.cmex.lesson2shoppinglist.data.myLog
import com.cmex.lesson2shoppinglist.domain.ShopItem
import javax.inject.Inject

class GetShopItemUseCase @Inject constructor(private val listener:InterfaceWorkToShopList) {
    fun getShopItemUC(id:Int):ShopItem{

       return listener.getShopItem(id)
    }
}