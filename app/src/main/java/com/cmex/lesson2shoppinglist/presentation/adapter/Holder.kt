package com.cmex.lesson2shoppinglist.presentation.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.cmex.lesson2shoppinglist.databinding.ItemActiveBinding
import com.cmex.lesson2shoppinglist.databinding.ItemInactiveBinding
import com.cmex.lesson2shoppinglist.domain.ShopItem

class Holder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setData(shopItem: ShopItem) {
        when(binding){
            is ItemActiveBinding ->{
                binding.tvName.text=shopItem.name
                binding.tvCount.text=shopItem.count.toString()
            }
            is ItemInactiveBinding ->{
                binding.tvName.text=shopItem.name
                binding.tvCount.text=shopItem.count.toString()
            }
        }
    }
}