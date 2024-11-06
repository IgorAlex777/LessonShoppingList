package com.cmex.lesson2shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.cmex.lesson2shoppinglist.R
import com.cmex.lesson2shoppinglist.data.ImplWorkShopList
import com.cmex.lesson2shoppinglist.data.myLog
import com.cmex.lesson2shoppinglist.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this)[ViewModelShoppingList::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        initObserverShopList()
        addShopItem()
        editShopItem()
        deleteShopItem()


    }
    private fun initObserverShopList(){
        viewModel.shopListViewModel.observe(this){
            val list=it
            myLog("shoppingList=$it")
        }
    }
    private fun editShopItem(){
          val shopItem=ShopItem("Oleg",7,true,1)
        viewModel.editShopItem(shopItem)
    }
    private fun addShopItem(){
        val shopItem=ShopItem("Igor",2,true)
        viewModel.addShopItem(shopItem)
    }
   private  fun deleteShopItem(){
        val shopItem=ImplWorkShopList.getShopItem(5)
        viewModel.removeShopItem(shopItem)
    }
}