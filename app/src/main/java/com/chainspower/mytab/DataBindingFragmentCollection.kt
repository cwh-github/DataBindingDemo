package com.chainspower.mytab

import android.database.DatabaseUtils
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.chainspower.model.Goods
import com.chainspower.model.ImageInfo
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
        mBinding.goods = Goods(12.6f, "cwh", "asd", "2019-06-11", 12, true)
        mBinding.flag = true


        mBtnCollection.setOnClickListener {
            onClick(it)
        }

        mBtnBinding.setOnClickListener {
            mBinding.goods!!.date.set("${Random().nextInt(24)}")
        }

        mBinding.imageInfo = ImageInfo(
            "https://www.gstatic.com/devrel-devsite/v376af9848533d30e0dd1d0914c8856dd94bfbc2392899dfed92ec7e10698aa74/android/images/lockup.svg",
            1024
        )
        mBtnBindingAdapter.setOnClickListener {
            mBinding.imageInfo!!.url.set("https://user-gold-cdn.xitu.io/2019/6/5/16b25f59a9b6fb66?imageView2/0/w/1280/h/960/format/webp/ignore-error/1${Random().nextInt()}")
        }

        mBtnAddText.setOnClickListener {
            mBinding.goods!!.date.set("Date is ${Random().nextInt(18)}")

            mBinding.flag = Random().nextBoolean()
        }
    }

    public fun onClick(view: View) {
        mBinding.map!!["name"] = "cwh ${Random().nextInt(27)}"
        mBinding.list!!.add(0, "${Random().nextInt(10)}")
    }

    companion object {

        @JvmStatic
        @BindingAdapter("url")
        fun loadImage(image: ImageView, url: String) {
            Glide.with(image.context).load(url).into(image)
            Toast.makeText(image.context, "Url", Toast.LENGTH_SHORT).show()
        }

        @JvmStatic
        @BindingAdapter("android:text")
        fun btnText(btn: Button, text: String) {
            btn.text = "$text ${Random().nextInt(18)}"
        }

//        @JvmStatic
//        @BindingConversion
//        fun btnTextConversion(text:String):String{
//            return "$text --ConversionString"
//        }
    }


}