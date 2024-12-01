package com.cmex.lesson2shoppinglist.data.mappers

import com.cmex.lesson2shoppinglist.data.db.ShopItemData
import com.cmex.lesson2shoppinglist.domain.ShopItem

class MappersShopItem {
    fun convertShopItemToShopItemData(shopItem: ShopItem) =ShopItemData(
       id= shopItem.id,
       name= shopItem.name,
       count= shopItem.count,
        active=shopItem.active)
    fun convertShopItemDataToShopItem(shopItemData: ShopItemData) =ShopItem(
       id= shopItemData.id,
       name= shopItemData.name,
       count= shopItemData.count,
        active=shopItemData.active)
    fun convertShopListDataToShopListItems(listData:List<ShopItemData>)=listData.map {
        convertShopItemDataToShopItem(it)
    }
}