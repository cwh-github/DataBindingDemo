package com.chainspower.navigation.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.chainspower.mytab.R
import kotlinx.android.synthetic.main.fragment2_layout.*

/**
 * Description:
 * Date：2019/7/9-17:04
 * Author: cwh
 */
class Fragment2 :Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment2_layout,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBtnGoFragment.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_mFragment2_to_mFragment3)
        }

        mBtnBackFragment.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }
    }

}