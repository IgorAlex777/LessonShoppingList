package com.cmex.lesson2shoppinglist.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.cmex.lesson2shoppinglist.domain.ShopItem

class DiffUtilShopItem:DiffUtil.ItemCallback<ShopItem>() {
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
       return oldItem==newItem
    }
}