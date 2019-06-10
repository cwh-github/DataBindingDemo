package com.chainspower.mytab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.chainspower.model.User
import com.chainspower.mytab.databinding.DataBindingFragmentData
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_databinding.*

/**
 * Description: binding Fragment
 * Date：2019/6/10-9:58
 * Author: cwh
 */

class DataBindingFragment:Fragment(){
    private var binding: DataBindingFragmentData?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =
            DataBindingUtil.inflate<DataBindingFragmentData>(inflater,R.layout.fragment_databinding,container,false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user= User("CWH","28")
        binding!!.userInfo=user
        binding!!.mTvUserName.setOnClickListener {
            onNameClick(it)
        }
    }

    private fun onNameClick(view: View){
        Snackbar.make(mTvUserName,mTvUserName.text, Snackbar.LENGTH_SHORT).show()
    }

}