package com.cmex.lesson2shoppinglist.data.di

import android.app.Application
import com.cmex.lesson2shoppinglist.data.ImplWorkShopList
import com.cmex.lesson2shoppinglist.data.InterfaceWorkToShopList
import com.cmex.lesson2shoppinglist.data.db.DataBase
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides

@Module
interface ModuleRepository {
    @AppScope
    @Binds
    fun bindRepository(impl:ImplWorkShopList):InterfaceWorkToShopList
    companion object{
        @AppScope
        @Provides
        fun providerDb(application: Application):DataBase=DataBase.getInstance(application   )
    }
}