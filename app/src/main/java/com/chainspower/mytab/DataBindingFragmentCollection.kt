package com.chainspower.mytab

import android.database.DatabaseUtils
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableArrayMap
import androidx.fragment.app.Fragment
import com.chainspower.mytab.databinding.DataBindingCollection
import kotlinx.android.synthetic.main.fragment_collection.*
import java.util.*

/**
 * Description:
 * Date：2019/6/10-16:25
 * Author: cwh
 */
class DataBindingFragmentCollection : Fragment() {

    lateinit var mBinding: DataBindingCollection
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_collection, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
        val list = ObservableArrayList<String>()
        list.add("C")
        list.add("W")
        list.add("H")
        val map = ObservableArrayMap<String, String>()
        map["name"] = "CWH"
        map["age"] = "27"
        mBinding.list = list
        mBinding.map = map
        mBinding.index = 1
        mBinding.key = "name"

        mBtnCollection.setOnClickListener {
            onClick(it)
        }
    }

    public fun onClick(view: View) {
        mBinding.map!!["name"] = "cwh ${Random().nextInt(27)}"
        mBinding.list!!.add(0,"${Random().nextInt(10)}")
    }


}