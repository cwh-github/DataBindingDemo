package com.chainspower.mytab

import android.os.Bundle
import android.util.Printer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingConversion
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.chainspower.mytab.databinding.DataBindingConversion

/**
 * Description:
 * Date：2019/6/11-14:59
 * Author: cwh
 */
class DataBindingFragmentConversion : Fragment() {


    lateinit var mBinding: DataBindingConversion
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_conversion, container, false)
        mBinding.lifecycleOwner=this
        return mBinding.root
    }


    companion object {

        //对布局文件中所有以@{string}的进行转换

        //@BindingConversion
        @JvmStatic
        fun mTvTextConversion(text: String): String {
            return "$text conversion"
        }
    }
}