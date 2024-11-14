package com.cmex.lesson2shoppinglist.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.cmex.lesson2shoppinglist.data.myLog
import com.cmex.lesson2shoppinglist.databinding.ActivityMainBinding
import com.cmex.lesson2shoppinglist.domain.ShopItem
import com.cmex.lesson2shoppinglist.presentation.ViewModelShoppingList
import com.cmex.lesson2shoppinglist.presentation.adapter.AdapterShopList
import com.cmex.lesson2shoppinglist.presentation.adapter.ListAdapterShopItems

class MainActivity : AppCompatActivity() {
    private val model by lazy { ViewModelProvider(this)[ViewModelShoppingList::class.java] }
    private lateinit var  listAdapter: ListAdapterShopItems
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
       listAdapter=ListAdapterShopItems()
       binding.rvShoppingList.layoutManager=LinearLayoutManager(this)
        binding.rvShoppingList.adapter=listAdapter
    }
    private fun initObserverShopList(){
        model.shopListViewModel.observe(this){
           listAdapter.submitList(it)
        }
    }
    private fun onClickLong(){
      listAdapter.listenerClickLong={
          val active=!it.active
          model.editShopItem(ShopItem(it.name,it.count,active=active,it.id))
      }
    }
    private fun onClickItem(){
        listAdapter.listenerClickItem={
           myLog("click")
        }
    }


}