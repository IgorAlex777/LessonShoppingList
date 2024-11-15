package com.cmex.lesson2shoppinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _errorInputName=MutableLiveData<Boolean>()
    val errorInputName:LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount=MutableLiveData<Boolean>()
    val errorInputCount:LiveData<Boolean>
        get() = _errorInputCount

     private  val _shopItemModel=MutableLiveData<ShopItem>()
    val shopItemModel:LiveData<ShopItem>
        get() = _shopItemModel

    private val _endSavingModel=MutableLiveData<Unit>()
    val endSavingModel:LiveData<Unit>
        get() = _endSavingModel
    fun getShopItem(id:Int):ShopItem{
       return getShopItemUseCase.getShopItemUC(id)
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
    fun onSaveShopItem(name: String,count: String,id:Int){
        if(onCheckingDataInput(name,count)){
            if(id==-1){
                addShopItem(ShopItem(name,count.toInt(),true))
                endSaving()
            } else{
                val shopItem=getShopItem(id).copy(name = name,count=count.toInt())
                editShopItem(shopItem)
                _shopItemModel.value=shopItem
                endSaving()
            }
        }
    }

 private fun onCheckingDataInput(name:String,count:String):Boolean{
        var flagResult=true
       if(onCheckingName(name).isBlank()){
           flagResult=false
          _errorInputName.value=true
       }
        if(onCheckingCount(count)<=0){
            flagResult=false
            _errorInputCount.value=true
        }
        return flagResult
    }
    private fun onCheckingName(name:String):String{
        return name.trim().ifEmpty { "" }
    }
    private fun onCheckingCount(count:String):Int{
        return try {
            count.toInt()
        }catch (e :Exception){
            0
        }
    }
    private fun endSaving(){
      _endSavingModel.value=Unit
    }
}