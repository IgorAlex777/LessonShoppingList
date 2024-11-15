package com.cmex.lesson2shoppinglist.presentation

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat

import com.cmex.lesson2shoppinglist.R
import com.cmex.lesson2shoppinglist.databinding.DialogDeleteItemBinding
import com.cmex.lesson2shoppinglist.domain.ShopItem

object MyDialog {
     const val YES_DELETE="yes_delete"
    const val  NO_DELETE="no_delete"
     var listenerDelete:((ShopItem)->Unit)?=null
    var listenerNoDelete:((Unit)->Unit)?=null
    fun dialogDeleteShopItem(context: Context, shopItem: ShopItem){

        val dialog= Dialog(context)
        val inflater=LayoutInflater.from(context)
        val binding= DialogDeleteItemBinding.inflate(inflater)
        dialog.window?.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.fon_layout))
        dialog.setContentView(binding.root)
        dialog.setCancelable(false)
        val dialogWH=dialog.window?.attributes
        dialogWH?.width=800
        dialogWH?.height=ViewGroup.LayoutParams.WRAP_CONTENT
        dialog.window?.attributes=dialogWH

        dialog.show()
        binding.ibNo.setOnClickListener {
            listenerNoDelete?.invoke(Unit)
            dialog.dismiss()
        }
        binding.ibYes.setOnClickListener {
         listenerDelete?.invoke(shopItem)
            dialog.dismiss()

        }

    }

}
