package com.chainspower.mytab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.transaction
import com.chainspower.model.User
import com.chainspower.mytab.databinding.DataBingDemo
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_data_binding.*

class DataBindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_data_binding)
        val binding =
            DataBindingUtil.setContentView<DataBingDemo>(this, R.layout.activity_data_binding)
        val user = User("CWH", "27")
        binding.userInfos = user
        binding.mTvName.setOnClickListener {
            onNameClick(it)
        }

        initFragment()


    }

    private fun initFragment() {
        supportFragmentManager.transaction(false, true) {
            this.replace(R.id.mFrame, DataBindingFragment())
        }

        supportFragmentManager.transaction {
            this.replace(R.id.mFrame1, DataBindingFragmentOther())
        }

        supportFragmentManager.transaction {
            this.replace(R.id.mFrame2,DataBindingFragmentCollection())
        }
    }

    private fun onNameClick(view: View) {
        Snackbar.make(mTvName, mTvName.text, Snackbar.LENGTH_SHORT).show()
    }
}
