package com.cmex.lesson2shoppinglist.data.di

import androidx.lifecycle.ViewModel
import com.cmex.lesson2shoppinglist.presentation.ViewModelShoppingList
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ModuleViewModels {
    @Binds
    @IntoMap
    @ViewModelKey(ViewModelShoppingList::class)
    fun bindViewModels(vm:ViewModelShoppingList):ViewModel
}