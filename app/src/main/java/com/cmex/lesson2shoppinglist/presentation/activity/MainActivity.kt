package com.cmex.lesson2shoppinglist.presentation.activity

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cmex.lesson2shoppinglist.R
import com.cmex.lesson2shoppinglist.data.myLog
import com.cmex.lesson2shoppinglist.databinding.ActivityMainBinding
import com.cmex.lesson2shoppinglist.domain.ShopItem
import com.cmex.lesson2shoppinglist.presentation.MyDialog
import com.cmex.lesson2shoppinglist.presentation.ViewModelShoppingList
import com.cmex.lesson2shoppinglist.presentation.adapter.ListAdapterShopItems
import com.cmex.lesson2shoppinglist.presentation.fragments.FragmentItem

class MainActivity : AppCompatActivity(),FragmentItem.ListenerClose {
    private val model by lazy { ViewModelProvider(this)[ViewModelShoppingList::class.java] }
    private lateinit var  listAdapter: ListAdapterShopItems
    private val hostActivity:AppCompatActivity?=null
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecyclerView()
        initObserverShopList()
        onClickLong()
        onClickItem()
        onClickAdd()
         checkingOrientationScreen()

    }
    private fun checkingOrientationScreen():Boolean{
      return  when(resources.configuration.orientation){
        Configuration.ORIENTATION_PORTRAIT->false
        Configuration.ORIENTATION_LANDSCAPE->true
          else -> {false}
      }
    }
    private fun onSetFragment(fragment: Fragment){
        hostActivity?.let {
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container,fragment)
                .addToBackStack(null)
                .commit()
        }

    }
    private fun initRecyclerView(){
       listAdapter= ListAdapterShopItems()
       binding.rvShoppingList.layoutManager=LinearLayoutManager(this)
        binding.rvShoppingList.adapter=listAdapter

        binding.rvShoppingList.recycledViewPool.setMaxRecycledViews(ACTIVE, POOL)
        binding.rvShoppingList.recycledViewPool.setMaxRecycledViews(INACTIVE, POOL)
        deleteShopItemSwipe().attachToRecyclerView(binding.rvShoppingList)
    }
    private fun initObserverShopList(){
        model.shopListViewModel.observe(this){
            if(it.isEmpty()){
                binding.ivEmptyList.visibility=View.VISIBLE
            }else binding.ivEmptyList.visibility=View.GONE
            myLog("observer list-it.size =${it.size}")
           listAdapter.submitList(it)
            myLog("listAdapter.size=${listAdapter.currentList.size}")
        }
    }
    private fun onClickAdd(){
        binding.ibtnAddItem.setOnClickListener {
            binding.container?.visibility= View.VISIBLE

            if(checkingOrientationScreen()) onSetFragment(FragmentItem.newInstanceADD())
           else startActivity(ItemActivity.newIntentAdd(this))
        }
    }
    private fun deleteShopItemSwipe():ItemTouchHelper{
        return ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
               return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                MyDialog.dialogDeleteShopItem(this@MainActivity,listAdapter.currentList[position])
                MyDialog.listenerNoDelete={
                    listAdapter.notifyItemChanged(position)
                }
                MyDialog.listenerDelete={
                    myLog("в Диалоге item=$it")
                    model.removeShopItem(it)
                }
            }

        })
            

    }
    private fun onClickLong(){
      listAdapter.listenerClickLong={
          val active=!it.active
          model.editShopItem(ShopItem(it.name,it.count,active=active,it.id))
      }
    }
    private fun onClickItem(){
        listAdapter.listenerClickItem={
            binding.container?.visibility= View.VISIBLE

            if(checkingOrientationScreen())onSetFragment(FragmentItem.newInstanceEdit(it.id))
          else   startActivity(ItemActivity.newIntentEdit(this,it.id))
        }

    }
    companion object{
        private const val ACTIVE=777
        private const val INACTIVE=888
       private const val POOL=10

    }

    override fun onCloseView() {
        binding.container?.visibility= View.GONE

       supportFragmentManager.popBackStack()
    }

}