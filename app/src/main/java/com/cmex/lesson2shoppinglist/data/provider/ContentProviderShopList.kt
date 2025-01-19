package com.cmex.lesson2shoppinglist.data.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.cmex.lesson2shoppinglist.data.dao.DaoShoppingList
import com.cmex.lesson2shoppinglist.data.db.DataBase
import com.cmex.lesson2shoppinglist.data.mappers.MappersShopItem
import com.cmex.lesson2shoppinglist.data.myLog
import com.cmex.lesson2shoppinglist.domain.ShopItem
import com.cmex.lesson2shoppinglist.presentation.activity.MyApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContentProviderShopList:ContentProvider() {
   // private val  myAppContext=MyApp.instance
    private lateinit var db :DataBase
    private val mapper=MappersShopItem()
      private lateinit var dao:DaoShoppingList

    private val uriMatcher=UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(AUTHORITY, PATH_SHOP_LIST, CODE_SHOP_LIST)
        addURI(AUTHORITY, PATH_ITEM_ID, CODE_ITEM_ID)
    }
    override fun onCreate(): Boolean {
        myLog("Context=$context")
        if(context!=null){
            db= DataBase.getInstance(context!!)
            dao=db.getDao()

        }

        return true

    }

    override fun query(
        uri: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        val code=uriMatcher.match(uri)
        myLog("content_provider query $uri code=$code")
     when(code){
         CODE_SHOP_LIST->{
             myLog("запрос  CODE_SHOP_LIST по uri=$uri")
            val cursor=  dao.getShopItemsCursor()
             myLog("code cursor=$cursor")
             return cursor
         }
           /* CODE_ITEM_ID->{
                myLog("запрос  CODE_ITEM_ID по uri=$uri")
            }*/
            else-> return null
        }


    }

    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, contentValues: ContentValues?): Uri? {
        val code=uriMatcher.match(uri)
        when(code){
            CODE_SHOP_LIST->{

                    if (contentValues == null) return null
                    CoroutineScope(Dispatchers.Main).launch{
                    val id = contentValues.getAsInteger("id")
                    val name = contentValues.getAsString("name")
                    val count = contentValues.getAsInteger("count")
                    val active = contentValues.getAsBoolean("active")
                    val shopItem = ShopItem(
                        id = id,
                        name = name,
                        count = count,
                        active = active
                    )
                        dao.addShopItemInDb(mapper.convertShopItemToShopItemData(shopItem))
                    }
            }
        }
        return null
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }
    companion object{
         const val AUTHORITY="com.cmex.lesson2shoppinglist"
         const val PATH_SHOP_LIST="shop_item"
         const val PATH_ITEM_ID="shop_item/#"
         const val CODE_ITEM_ID=777
        const val CODE_SHOP_LIST=888

    }
}