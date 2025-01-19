package com.cmex.lesson2shoppinglist.presentation.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cmex.lesson2shoppinglist.R

import com.cmex.lesson2shoppinglist.data.myLog
import com.cmex.lesson2shoppinglist.databinding.FragmentItemBinding
import com.cmex.lesson2shoppinglist.domain.ShopItem
import com.cmex.lesson2shoppinglist.presentation.ViewModelFactoryShopList
import com.cmex.lesson2shoppinglist.presentation.ViewModelShoppingList
import com.cmex.lesson2shoppinglist.presentation.activity.ItemActivity
import com.cmex.lesson2shoppinglist.presentation.activity.MyApp
import javax.inject.Inject

class FragmentItem : Fragment() {
    @Inject
    lateinit var viewModelFactory:ViewModelFactoryShopList
   private var modeScreen= NO_MODE
    private var idShopItem= NO_ID
    private lateinit var model:ViewModelShoppingList
    private lateinit var binding:FragmentItemBinding
    private lateinit var shopItem: ShopItem
    private var activityContext:Context?=null
    private val component by lazy{
        (requireActivity().application as MyApp).component
    }

    lateinit var listenerClose:ListenerClose
    override fun onAttach(context: Context) {
        super.onAttach(context)
             component.inject(this)
             activityContext=context
        myLog("context=$activityContext")
       if (context is ListenerClose) listenerClose=context
        else throw RuntimeException("подключить Interface ListenerClose")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentItemBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model=ViewModelProvider(this,viewModelFactory)[ViewModelShoppingList::class.java]
        onGetArguments()
          initDataBindingInFragment()
        settingDataOnScreen()

        onClickSaveShopItem()
        initObservers()
        onClearError()
    }
    //++++++++++++++++
    private fun initDataBindingInFragment(){
        binding.dataModel=model           // не забывать инициализацию
        binding.lifecycleOwner=viewLifecycleOwner //  не забывать
    }
    private fun initObservers(){
        model.endSavingModel.observe(viewLifecycleOwner){
            listenerClose.onCloseView()
        }
       model.shopItemModel.observe(viewLifecycleOwner){
           shopItem=it
           myLog("shopItem???=$shopItem")
           binding.tvTitle.text=getString(R.string.edit_item)
           binding.etName.setText(shopItem.name)
           binding.etCountItem.setText(shopItem.count.toString())
       }
    }
    private  fun onClickSaveShopItem(){
        binding.ibtnSave.setOnClickListener {
            val name=binding.etName.text.toString()
            val count=binding.etCountItem.text.toString()
           // model.onSaveShopItem(name,count,idShopItem)
            model.onAddContentResolver(requireContext(),name,count.toInt())
        }
    }
    private fun settingDataOnScreen(){

        if(modeScreen!= ADD_ITEM && modeScreen!= EDIT_ITEM){
            throw RuntimeException("получили не известный режим $modeScreen")
        }

        if(modeScreen== ADD_ITEM){
            binding.tvTitle.text = getString(R.string.add_item)
        }

    }
    private fun onGetArguments(){
      val args=requireArguments()
       if (!args.containsKey(SELECT)){
           throw RuntimeException("не получены данные по выбору режима EDIT или ADD на фрагменте")
       }
        modeScreen= args.getString(SELECT, NO_MODE)

        if(modeScreen== EDIT_ITEM){
            if(!args.containsKey(ID_ITEM)){
                throw RuntimeException("нет данных по ID ShopItem на фрагменте")
            }
            idShopItem=args.getInt(ID_ITEM, NO_ID)
            myLog("id=$idShopItem")

            model.getShopItem(idShopItem)

        }
    }
    private fun onClearError(){
        binding.etCountItem.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
              binding.tilCount.error=null
            }
        })
        binding.etName.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
               binding.tiName.error=null
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()

        listenerClose.onCloseView()
    }
//*************************************************************************************
    companion object {
        private const val EDIT_ITEM="edit"
        private const val ADD_ITEM="add"
        private const val ID_ITEM="id"
        private const val SELECT="mode"
        private const val NO_MODE=""
        private  const val NO_ID=0

        fun newInstanceEdit(id:Int) =
            FragmentItem().apply {
                arguments = Bundle().apply {
                  putString(SELECT, EDIT_ITEM)
                    putInt(ID_ITEM,id)
                }
            }
        fun newInstanceADD()=FragmentItem().apply {
            arguments = Bundle().apply {
                putString(SELECT, ADD_ITEM)

            }
        }
    }
    interface ListenerClose{
        fun onCloseView()
    }
}