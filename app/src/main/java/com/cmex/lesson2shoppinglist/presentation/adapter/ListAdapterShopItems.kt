package com.cmex.lesson2shoppinglist.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.cmex.lesson2shoppinglist.R
import com.cmex.lesson2shoppinglist.databinding.ItemActiveBinding
import com.cmex.lesson2shoppinglist.databinding.ItemInactiveBinding
import com.cmex.lesson2shoppinglist.databinding.ItemShopBinding
import com.cmex.lesson2shoppinglist.domain.ShopItem

class ListAdapterShopItems : ListAdapter<ShopItem,ListAdapterShopItems.Holder>(DiffUtilShopItem()){
    private lateinit var  binding:ViewDataBinding
    private lateinit var  context: Context
    var listenerClickLong:((ShopItem)->Unit)?=null
    var listenerClickItem:((ShopItem)->Unit)?=null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater=LayoutInflater.from(parent.context)

        val layoutId=if(viewType== ACTIVE) R.layout.item_active
        else R.layout.item_inactive
// DataBindingUtil где есть установка layout
        binding=DataBindingUtil.inflate(inflater,layoutId,parent,false)


        return Holder(binding)
    }


    override fun getItemViewType(position: Int): Int {
     return  if(getItem(position).active) ACTIVE
        else INACTIVE
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnLongClickListener {
            listenerClickLong?.let { it1 -> it1(getItem(position)) }
           // listenerClickLong?.invoke(shopList[position])
            true
        }
        holder.itemView.setOnClickListener {
            listenerClickItem?.invoke(getItem(position))
        }
        return holder.setData(getItem(position))
    }
    class Holder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {


        fun setData(shopItem: ShopItem) {
           when(binding){
               is ItemActiveBinding->{
                   binding.tvName.text=shopItem.name
                   binding.tvCount.text=shopItem.count.toString()
               }
               is ItemInactiveBinding->{
                   binding.tvName.text=shopItem.name
                   binding.tvCount.text=shopItem.count.toString()
               }
           }
        }
    }
        companion object{
            private const val ACTIVE=777
            private const val INACTIVE=888

        }

    }



