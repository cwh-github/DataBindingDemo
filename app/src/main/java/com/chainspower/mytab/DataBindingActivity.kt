package com.chainspower.mytab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.OnRebindCallback
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
        binding.setVariable(BR.userInfo,User("CWH_C","28"))

        initFragment()

        binding.addOnRebindCallback(object:OnRebindCallback<DataBingDemo>(){
            /**
             * 绑定完成
             */
            override fun onBound(binding: DataBingDemo?) {
                super.onBound(binding)
            }

            override fun onCanceled(binding: DataBingDemo?) {
                super.onCanceled(binding)
            }

            /**
             * return true 绑定视图，false 解除绑定
             */
            override fun onPreBind(binding: DataBingDemo?): Boolean {
                return super.onPreBind(binding)
            }
        })


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

        supportFragmentManager.transaction {
            this.replace(R.id.mFrame3,DataBindingFragmentConversionJava())
        }
    }

    private fun onNameClick(view: View) {
        Snackbar.make(mTvName, mTvName.text, Snackbar.LENGTH_SHORT).show()
    }
}
