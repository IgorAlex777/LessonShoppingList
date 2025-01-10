package com.cmex.lesson2shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cmex.lesson2shoppinglist.domain.ShopItem
import javax.inject.Inject
import kotlin.random.Random

class ImplWorkShopList @Inject constructor()  : InterfaceWorkToShopList{
    private val shopListLiveData=MutableLiveData<List<ShopItem>>()
  private  val shopList= mutableListOf <ShopItem>()
   

    init {
        for(i in 0 ..25) {
            val number= (0..1).random()
            val active = number != 0

          val shopItem=ShopItem("name$i",i,active,i)
          shopList.add(shopItem)
        }
    }

    override fun getShoppingList(): LiveData<List<ShopItem>> {
        updateShopList()
        return shopListLiveData
    }

    override fun getShopItem(id:Int): ShopItem {
        shopList.find { it.id==id}?.let { return it }
        return ShopItem("empty",0,false,-1)
    }

    override fun removeShopItem(shopItem: ShopItem) {
      shopList.remove(shopItem)
        updateShopList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldShopItem= getShopItem(shopItem.id)
        if(shopItem.name!="empty") {
            shopList.remove(oldShopItem)
            addShopItem(shopItem)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if(shopItem.id==-1){
            val shopItemTemp=shopItem.copy(id= shopList.size)
            shopList.add(shopItemTemp)
           
        }else{
            shopList.add(shopItem)
            shopList.sortWith(compareBy{ it.id })
        }
        updateShopList()
    }

    private fun  updateShopList(){
       shopListLiveData.value= shopList.toList()
   }

}