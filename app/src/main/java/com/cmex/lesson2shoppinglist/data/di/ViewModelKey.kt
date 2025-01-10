package com.cmex.lesson2shoppinglist.data.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val viewModelName:KClass<out ViewModel>)
