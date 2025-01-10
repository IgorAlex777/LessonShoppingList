package com.cmex.lesson2shoppinglist.data.di

import com.cmex.lesson2shoppinglist.presentation.activity.MainActivity
import com.cmex.lesson2shoppinglist.presentation.fragments.FragmentItem
import dagger.Component

@Component(modules = [ModuleRepository::class,ModuleViewModels::class])
interface ComponentShopList {
    fun inject(activity: MainActivity)
    fun inject(fragmentItem: FragmentItem)
}