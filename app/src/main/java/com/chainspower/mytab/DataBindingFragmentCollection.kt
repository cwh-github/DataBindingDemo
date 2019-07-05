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
import com.chainspower.model.Progress
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
        mBinding.orderCode="001"
        mBinding.progress= Progress(ObservableInt(18))


        mBtnCollection.setOnClickListener {
            onClick(it)
        }

        mBtnBinding.setOnClickListener {
            mBinding.goods!!.date.set("${Random().nextInt(24)}")
        }

        mBinding.imageInfo = ImageInfo(
            "https://www.baidu.com/s?wd=%E4%BB%8A%E6%97%A5%E6%96%B0%E9%B2%9C%E4%BA%8B&tn=SE_PclogoS_8whnvm25&sa=ire_dl_gh_logo&rsv_dl=igh_logo_pcs",
            1024
        )
        mBtnBindingAdapter.setOnClickListener {
            mBinding.imageInfo!!.url.set("https://profile.csdnimg.cn/E/B/E/1_u011212909")
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

//        @JvmStatic
//        @BindingAdapter("url")
//        fun loadImage(image: ImageView, url: String) {
//            Glide.with(image.context).load(url).into(image)
//            Toast.makeText(image.context, "Url", Toast.LENGTH_SHORT).show()
//        }
//
//        @JvmStatic
//        @BindingAdapter("toast")
//        fun toast(image: ImageView, text: String) {
//            Toast.makeText(image.context, text, Toast.LENGTH_SHORT).show()
//        }
//
//        @JvmStatic
//        @BindingAdapter("android:text")
//        fun btnText(btn: Button, text: String) {
//            btn.text = "$text ${Random().nextInt(18)}"
//        }

//        @JvmStatic
//        @BindingConversion
//        fun btnTextConversion(text:String):String{
//            return "$text --ConversionString"
//        }
    }


}