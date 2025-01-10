package com.cmex.lesson2shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.cmex.lesson2shoppinglist.data.db.DataBase
import com.cmex.lesson2shoppinglist.data.mappers.MappersShopItem
import com.cmex.lesson2shoppinglist.domain.ShopItem
import com.cmex.lesson2shoppinglist.presentation.activity.MyApp
import javax.inject.Inject

class ImplWorkShopList @Inject constructor(private val mapper:MappersShopItem) : InterfaceWorkToShopList{

    private val context = MyApp.instance
    private val db by lazy { DataBase.getInstance(context) }
    private val dao = db.getDao()




    override fun getShoppingList(): LiveData<List<ShopItem>> = dao.getShopItemsFromDb().map {
      mapper.convertShopListDataToShopListItems(it)}

   override suspend fun getShopItem(id:Int): ShopItem = mapper.convertShopItemDataToShopItem(dao.getShopItem(id))


    /*override fun getShopItem(id:Int): LiveData<ShopItem> = dao.getShopItem(id).map{
      mapper.convertShopItemDataToShopItem(it)
    }*/
    override suspend fun removeShopItem(shopItem: ShopItem) {

       dao.deleteShopItem(mapper.convertShopItemToShopItemData(shopItem).id)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
      dao.editShopItem(mapper.convertShopItemToShopItemData(shopItem))
    }

    override suspend fun addShopItem(shopItem: ShopItem) {
       dao.addShopItemInDb(mapper.convertShopItemToShopItemData(shopItem))
    }



}