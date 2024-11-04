package com.cmex.lesson2shoppinglist.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cmex.lesson2shoppinglist.domain.ShopItem
import kotlinx.coroutines.flow.Flow


@Dao
interface DaoShoppingList {
   @Insert()
    suspend fun insertShopItemToList(shopItem: ShopItem)

    @Update
   suspend fun editShopItem(shopItem: ShopItem)

   @Query("SELECT *  FROM Shopping_List")
    fun getSoppingList(): Flow<List<ShopItem>>

   @Query("SELECT * FROM Shopping_List WHERE id LIKE :itemId")
   fun getShopItem(itemId:Int):Flow<ShopItem>

   @Query("DELETE FROM Shopping_List WHERE id IS :itemId")
   suspend   fun removeShopItem(itemId: Int)
}