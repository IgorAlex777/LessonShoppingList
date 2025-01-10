package com.cmex.lesson2shoppinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmex.lesson2shoppinglist.data.ImplWorkShopList
import com.cmex.lesson2shoppinglist.data.usecase.AddShopItemUseCase
import com.cmex.lesson2shoppinglist.data.usecase.EditShopItemUseCase
import com.cmex.lesson2shoppinglist.data.usecase.GetShopItemUseCase
import com.cmex.lesson2shoppinglist.data.usecase.GetShoppingListUseCase
import com.cmex.lesson2shoppinglist.data.usecase.RemoveShopItemUseCase
import com.cmex.lesson2shoppinglist.domain.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelShoppingList @Inject constructor(
    private val getShoppingListUseCase: GetShoppingListUseCase,
    private val getShopItemUseCase: GetShopItemUseCase,
    private val editShopItemUseCase: EditShopItemUseCase,
    private val removeShopItemUseCase: RemoveShopItemUseCase,
    private val addShopItemUseCase: AddShopItemUseCase
) : ViewModel() {


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

   fun getShopItem(id:Int){
       viewModelScope.launch {
           val item= getShopItemUseCase.getShopItemUC(id)
         _shopItemModel.postValue(item)
       }
   }



    fun editShopItem(shopItem: ShopItem){
        viewModelScope.launch {
            editShopItemUseCase.editShopItemUC(shopItem)
        }
    }
    fun removeShopItem(shopItem: ShopItem){
        viewModelScope.launch {
            removeShopItemUseCase.removeShopItemUC(shopItem)
        }
    }
    private fun addShopItem(shopItem: ShopItem){
        viewModelScope.launch {
            addShopItemUseCase.addShopItemUC(shopItem)
        }
    }
    fun onSaveShopItem(name:String, count: String,id:Int) {

        if (id == 0) {
            if (onCheckingDataInput(name, count)) {
                addShopItem(ShopItem(name, count.toInt(), true))
                endSaving()
            }
        } else {
            if (onCheckingDataInput(name, count)) {
                getShopItem(id)
                _shopItemModel.value?.let {
                    val tempShopItem=it.copy(name = name,count=count.toInt())
                    editShopItem(tempShopItem)
                }

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