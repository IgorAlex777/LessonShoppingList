package com.cmex.lesson2shoppinglist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.cmex.lesson2shoppinglist.R
import com.cmex.lesson2shoppinglist.domain.ShopItem

class ListAdapterShopItems : ListAdapter<ShopItem,HolderItem>(DiffUtilShopItem()){
    private lateinit var  binding:ViewDataBinding

    var listenerClickLong:((ShopItem)->Unit)?=null
    var listenerClickItem:((ShopItem)->Unit)?=null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderItem {
        val inflater=LayoutInflater.from(parent.context)

        val layoutId=if(viewType== ACTIVE) R.layout.item_active
        else R.layout.item_inactive
// DataBindingUtil где есть установка layout
        binding=DataBindingUtil.inflate(inflater,layoutId,parent,false)


        return HolderItem(binding)
    }


    override fun getItemViewType(position: Int): Int {
     return  if(getItem(position).active) ACTIVE
        else INACTIVE
    }
    override fun onBindViewHolder(holder: HolderItem, position: Int) {
        holder.itemView.setOnLongClickListener {
            listenerClickLong?.let { it1 -> it1(getItem(position)) }

            true
        }
        holder.itemView.setOnClickListener {
            listenerClickItem?.invoke(getItem(position))
        }
        return holder.setData(getItem(position))
    }

        companion object{
            private const val ACTIVE=777
            private const val INACTIVE=888

        }

    }



