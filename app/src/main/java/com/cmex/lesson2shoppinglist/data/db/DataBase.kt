package com.cmex.lesson2shoppinglist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cmex.lesson2shoppinglist.data.dao.DaoShoppingList

@Database(entities = [ShopItemData::class], version = 1)
abstract class DataBase :RoomDatabase(){
    abstract fun getDao(): DaoShoppingList
    companion object{
        const val DB_NAME="shop_items.db"
        private val LOCK=Any()
        private val INSTANCE:DataBase?=null
        fun getInstance(context: Context): DataBase {
            INSTANCE?.let {  //double checking
                return it
            }
            synchronized(LOCK){
                INSTANCE?.let { return it } //double checking //.allowMainThreadQueries()
                val tempDb= Room.databaseBuilder(context,DataBase::class.java,DB_NAME).build()
                val db=tempDb
                return tempDb
            }
        }
    }
}