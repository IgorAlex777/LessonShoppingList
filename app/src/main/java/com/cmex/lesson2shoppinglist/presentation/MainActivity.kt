package com.cmex.lesson2shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cmex.lesson2shoppinglist.data.myLog
import com.cmex.lesson2shoppinglist.databinding.ActivityMainBinding
import com.cmex.lesson2shoppinglist.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private val model by lazy { ViewModelProvider(this)[ViewModelShoppingList::class.java] }
    private lateinit var  adapterShopList :AdapterShopList
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecyclerView()
        initObserverShopList()
        onClickLong()
        onClickItem()
    }
    private fun initRecyclerView(){
        adapterShopList= AdapterShopList()
       binding.rvShoppingList.layoutManager=LinearLayoutManager(this)
        binding.rvShoppingList.adapter=adapterShopList
    }
    private fun initObserverShopList(){
        model.shopListViewModel.observe(this){
            adapterShopList.shopList=it as MutableList<ShopItem>
        }
    }
    private fun onClickLong(){
      adapterShopList.listenerClickLong={
          val active=!it.active
          model.editShopItem(ShopItem(it.name,it.count,active=active,it.id))
      }
    }
    private fun onClickItem(){
        adapterShopList.listenerClickItem={
           myLog("click")
        }
    }

}