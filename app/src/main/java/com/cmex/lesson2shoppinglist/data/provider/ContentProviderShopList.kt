package com.cmex.lesson2shoppinglist.data.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.cmex.lesson2shoppinglist.data.myLog

class ContentProviderShopList:ContentProvider() {

    val uriMatcher=UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(AUTHORITY, PATH_SHOP_LIST, CODE_SHOP_LIST)
        addURI(AUTHORITY, PATH_ITEM_ID, CODE_ITEM_ID)
    }
    override fun onCreate(): Boolean {
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
        when(code){
         CODE_SHOP_LIST->{
             myLog("запрос  CODE_SHOP_LIST по uri=$uri")
         }
            CODE_ITEM_ID->{
                myLog("запрос  CODE_ITEM_ID по uri=$uri")
            }
        }
        myLog("content_provider query $uri code=$code")
       return null
    }

    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        TODO("Not yet implemented")
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