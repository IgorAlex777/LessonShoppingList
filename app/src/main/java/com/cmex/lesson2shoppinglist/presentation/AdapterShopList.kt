package com.cmex.lesson2shoppinglist.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.cmex.lesson2shoppinglist.R
import com.cmex.lesson2shoppinglist.data.myLog
import com.cmex.lesson2shoppinglist.databinding.ItemShopBinding
import com.cmex.lesson2shoppinglist.domain.ShopItem

class AdapterShopList :RecyclerView.Adapter<AdapterShopList.Holder>(){
    private lateinit var binding: ItemShopBinding
    private lateinit var  context: Context
    var listenerClickLong:((ShopItem)->Unit)?=null
    var listenerClickItem:((ShopItem)->Unit)?=null
    var shopList= mutableListOf<ShopItem>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field=value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater=LayoutInflater.from(parent.context)


        binding=ItemShopBinding.inflate(inflater,parent,false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnLongClickListener {
            holder.itemView.animate()
                .scaleX(0.8f)
                .scaleY(0.8f)
                .setDuration(500)
                .start()
            listenerClickLong?.let { it1 -> it1(shopList[position]) }
           // listenerClickLong?.invoke(shopList[position])
            true
        }
        holder.itemView.setOnClickListener {
            listenerClickItem?.invoke(shopList[position])
        }
        return holder.setData(shopList[position])
    }
    class Holder(private val binding: ItemShopBinding) : RecyclerView.ViewHolder(binding.root){
        private  val context=binding.root.context
        fun setData(shopItem: ShopItem){
            binding.tvNameItem.text=shopItem.name
            binding.tvCountItem.text=shopItem.count.toString()
             if(shopItem.active) {
                 binding.clShoppingList.setBackgroundResource(R.drawable.fon_my)
                 binding.tvNameItem.setTextColor(context.getColor(R.color.black))
                 binding.tvCountItem.setTextColor(context.getColor(R.color.black))
             } else {
                 binding.clShoppingList.setBackgroundResource(R.drawable.fon_item_true)
                 binding.tvNameItem.setTextColor(context.getColor(R.color.gray00))
                 binding.tvCountItem.setTextColor(context.getColor(R.color.grey))
             }
            }
        }
    }



