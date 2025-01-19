package com.cmex.lesson2shoppinglist.data.dao

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.cmex.lesson2shoppinglist.data.db.ShopItemData


@Dao
interface DaoShoppingList {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun addShopItemInDb(shopItem: ShopItemData)

    @Query("SELECT *  FROM shop_item")
    fun getShopItemsFromDb():LiveData<List<ShopItemData>>

    @Query("SELECT *  FROM shop_item")
    fun getShopItemsCursor():Cursor

   @Update
   suspend  fun editShopItem(shopItem: ShopItemData)

    @Query("SELECT * FROM shop_item WHERE id LIKE :itemId")
 suspend fun getShopItem(itemId:Int):ShopItemData

   @Query("DELETE FROM shop_item WHERE id IS :itemId")
 suspend    fun deleteShopItem(itemId: Int)
}