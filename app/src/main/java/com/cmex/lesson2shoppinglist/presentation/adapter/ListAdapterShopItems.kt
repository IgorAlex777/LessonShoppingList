package com.cmex.lesson2shoppinglist.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cmex.lesson2shoppinglist.R
import com.cmex.lesson2shoppinglist.databinding.ItemShopBinding
import com.cmex.lesson2shoppinglist.domain.ShopItem

class ListAdapterShopItems : ListAdapter<ShopItem,ListAdapterShopItems.Holder>(DiffUtilShopItem()){

    private lateinit var  context: Context
    var listenerClickLong:((ShopItem)->Unit)?=null
    var listenerClickItem:((ShopItem)->Unit)?=null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater=LayoutInflater.from(parent.context)
        val layoutId=if(viewType== ACTIVE) R.layout.item_active
        else R.layout.item_inactive

        val view =inflater.inflate(layoutId,parent,false)
        return Holder(view)
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
    class Holder( view: View) : RecyclerView.ViewHolder(view){
    private  val name=view.findViewById<TextView>(R.id.tvName)
    private  val count=view.findViewById<TextView>(R.id.tvCount)

        fun setData(shopItem: ShopItem){
           name.text=shopItem.name
         count.text=shopItem.count.toString()

            }
        }
    companion object{
        private const val ACTIVE=777
        private const val INACTIVE=888
        const val POOL=10
    }
    }



