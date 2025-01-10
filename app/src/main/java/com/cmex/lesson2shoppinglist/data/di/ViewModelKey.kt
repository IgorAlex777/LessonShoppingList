package com.cmex.lesson2shoppinglist.data.di

import androidx.lifecycle.ViewModel
import com.cmex.lesson2shoppinglist.presentation.ViewModelShoppingList
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Retention(AnnotationRetention.RUNTIME)
@ViewModelKey(ViewModelShoppingList::class)
annotation class ViewModelKey(val viewModelName:KClass<out ViewModel>)
