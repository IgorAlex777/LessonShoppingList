package com.cmex.lesson2shoppinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.cmex.lesson2shoppinglist.data.ImplWorkShopList
import com.cmex.lesson2shoppinglist.data.usecase.AddShopItemUseCase
import com.cmex.lesson2shoppinglist.data.usecase.EditShopItemUseCase
import com.cmex.lesson2shoppinglist.data.usecase.GetShopItemUseCase
import com.cmex.lesson2shoppinglist.data.usecase.GetShoppingListUseCase
import com.cmex.lesson2shoppinglist.data.usecase.RemoveShopItemUseCase
import com.cmex.lesson2shoppinglist.domain.ShopItem

class ViewModelShoppingList(application: Application):AndroidViewModel(application) {
    private val listener=ImplWorkShopList
    private val getShoppingListUseCase=GetShoppingListUseCase(listener)
    private val getShopItemUseCase=GetShopItemUseCase(listener)
    private val editShopItemUseCase=EditShopItemUseCase(listener)
    private val removeShopItemUseCase=RemoveShopItemUseCase(listener)
    private val addShopItemUseCase=AddShopItemUseCase(listener)
    val shopListViewModel=getShoppingListUseCase.getShoppingListUC()

    fun getShopItemModel(id:Int){
        getShopItemUseCase.getShopItemUC(id)
    }
    fun editShopItem(shopItem: ShopItem){
        editShopItemUseCase.editShopItemUC(shopItem)
    }
    fun removeShopItem(shopItem: ShopItem){
        removeShopItemUseCase.removeShopItemUC(shopItem)
    }
    fun addShopItem(shopItem: ShopItem){
        addShopItemUseCase.addShopItemUC(shopItem)
    }
}