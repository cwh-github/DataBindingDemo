package com.chainspower.mytab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.OnRebindCallback
import androidx.fragment.app.transaction
import androidx.lifecycle.*
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
        binding.lifecycleOwner
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

        //我们可以在线程中更改数 DataBinding 中被绑定的数据，但前提是该数据不能是集合。
        //
        //之所以可以这样，是因为 DataBinding 在处理数据时会对 变量/字段 进行值拷贝，这样就避免了并发问题。
        mBtn.setOnClickListener {
            Thread{
                Thread.sleep(500)
                binding.userInfos=User("CWH_ON_THREAD","27")
            }.start()
        }
        


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
