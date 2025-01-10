package com.cmex.lesson2shoppinglist.data.di

import com.cmex.lesson2shoppinglist.data.ImplWorkShopList
import com.cmex.lesson2shoppinglist.data.InterfaceWorkToShopList
import dagger.Binds
import dagger.Module

@Module
interface ModelRepository {
    @Binds
    fun repositoryShopItem(impl:ImplWorkShopList):InterfaceWorkToShopList

}