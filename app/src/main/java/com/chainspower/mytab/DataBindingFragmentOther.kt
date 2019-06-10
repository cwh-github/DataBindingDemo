package com.chainspower.mytab

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.fragment.app.Fragment
import com.chainspower.model.Goods
import com.chainspower.mytab.databinding.DataBindingNotify
import java.util.*

/**
 * Description:单向绑定，数据model变化，view显示变化
 * Date：2019/6/10-14:08
 * Author: cwh
 */
class DataBindingFragmentOther : Fragment() {

    private val TAG = "DataBinding"

    lateinit var binding: DataBindingNotify
    lateinit var goods: Goods
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_other_one, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goods = Goods(12.6f, "CWH_GOODS", "Gooods Details", "2019-06-10", 12, true)
        binding.goods = goods
        binding.goodsHandler = GoodsHandler()
        goods.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                when (propertyId) {
                    BR.price -> {
                        Log.d(TAG, "Price change")
                    }

                    BR._all -> {
                        Log.d(TAG, "All change")
                    }

                    else -> {
                        Log.d(TAG, "Other change")
                    }
                }
            }

        })
    }


    public inner class GoodsHandler {

        public fun changeGoodsPrice() {
            goods.setGoodsName("${Random().nextInt(10)} Goods Name")
            goods.setPrice(Random().nextFloat())

        }

        public fun changeDetails() {
            goods.setDetails("Goods Details is ${Random().nextInt()}")
            goods.price = (Random().nextFloat())
            goods.goodsName = "${Random().nextInt(10)} Goods Name"
        }

        public fun changeObservable() {
            goods.date.set("2020-10-01")
            goods.nums.set(Random().nextInt(10)+20)
            goods.isHave.set(Random().nextBoolean())
            binding.mTvHave.text=if(goods.isHave.get()) "有" else "没有"
        }
    }
}