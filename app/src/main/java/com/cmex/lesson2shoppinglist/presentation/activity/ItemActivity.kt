package com.cmex.lesson2shoppinglist.presentation.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.cmex.lesson2shoppinglist.R
import com.cmex.lesson2shoppinglist.data.myLog
import com.cmex.lesson2shoppinglist.data.utilSetColorStatusBar
import com.cmex.lesson2shoppinglist.presentation.fragments.FragmentItem

class ItemActivity : AppCompatActivity(),FragmentItem.ListenerClose {
    private var modeScreen= NO_MODE
    private var idShopItem= NO_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        myLog("onCreate")


        onGetIntent()
        utilSetColorStatusBar(this,ContextCompat.getColor(this,R.color.grey))
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.item_container,fragment)
            .addToBackStack(null)
            .commit()

    }
    private fun setScreenMode(){
        when(modeScreen){
            EDIT_ITEM->{setFragment(FragmentItem.newInstanceEdit(idShopItem))}
            ADD_ITEM->{setFragment(FragmentItem.newInstanceADD())}
        }
    }
    private fun onGetIntent(){
        if(!intent.hasExtra(SELECT_MODE)){
            throw RuntimeException("не получены данные по выбору режима EDIT или ADD")
        }
       modeScreen=intent.getStringExtra(SELECT_MODE).toString()

        if (modeScreen== EDIT_ITEM){
            if(!intent.hasExtra(ID_ITEM)){
                throw RuntimeException("нет данных по ID ShopItem")
            }
            idShopItem=intent.getIntExtra(ID_ITEM, NO_ID)
        }
        setScreenMode()
    }
    companion object{
        private const val EDIT_ITEM ="edit"
        private const val ADD_ITEM="add"
        private const val ID_ITEM="id_item"
        private const val NO_ID=-1
        private const val NO_MODE=""
        private const val SELECT_MODE="selection"
        fun newIntentAdd(context: Context):Intent{
            return Intent(context,ItemActivity::class.java).putExtra(SELECT_MODE, ADD_ITEM)
        }
        fun newIntentEdit(context: Context,id:Int):Intent{
            return Intent(context,ItemActivity::class.java).apply {
                putExtra(SELECT_MODE, EDIT_ITEM)
                putExtra(ID_ITEM,id)
            }
        }
    }

    override fun onCloseView() {
      finish()
    }
}