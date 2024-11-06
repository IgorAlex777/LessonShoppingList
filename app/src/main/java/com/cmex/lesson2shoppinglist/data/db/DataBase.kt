package com.cmex.lesson2shoppinglist.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cmex.lesson2shoppinglist.data.dao.DaoShoppingList

//@Database(entities = [ShopItem::class], version = 1)
abstract class DataBase :RoomDatabase(){
    abstract fun getDao(): DaoShoppingList
    companion object{
        const val DB="shopping_list.db"
        private val LOCK=Any()
        private val db:DataBase?=null
        fun getInstance(context: Context): DataBase {
            synchronized(LOCK){
                db?.let { return it }
                val tempDb= Room.databaseBuilder(context,DataBase::class.java,DB).build()
                val db=tempDb
                return tempDb
            }
        }
    }
}