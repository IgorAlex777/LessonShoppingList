package com.cmex.lesson2shoppinglist.data.usecase

import androidx.lifecycle.LiveData
import com.cmex.lesson2shoppinglist.data.InterfaceWorkToShopList
import com.cmex.lesson2shoppinglist.domain.ShopItem
import javax.inject.Inject

class GetShoppingListUseCase @Inject constructor(private val listener: InterfaceWorkToShopList){
    fun getShoppingListUC():LiveData<List<ShopItem>>{
      return listener.getShoppingList()
    }
}