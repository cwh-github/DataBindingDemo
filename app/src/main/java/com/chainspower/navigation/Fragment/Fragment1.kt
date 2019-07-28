package com.chainspower.navigation.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.chainspower.mytab.R
import kotlinx.android.synthetic.main.fragment1_layout.*

/**
 * Description:
 * Date：2019/7/9-17:04
 * Author: cwh
 */
class Fragment1 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment1_layout,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBtnGoFragment.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_mFragment1_to_mFragment2)
        }
    }

}