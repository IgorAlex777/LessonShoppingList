package com.cmex.lesson2shoppinglist.data.di

import android.app.Application
import com.cmex.lesson2shoppinglist.presentation.activity.MainActivity
import com.cmex.lesson2shoppinglist.presentation.fragments.FragmentItem
import dagger.BindsInstance
import dagger.Component
@AppScope
@Component(modules = [ModuleRepository::class,ModuleViewModels::class])
interface ComponentShopList {
    fun inject(activity: MainActivity)
    fun inject(fragmentItem: FragmentItem)
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ComponentShopList
    }
}