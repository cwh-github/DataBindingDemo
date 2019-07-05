package com.chainspower.somebind

import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.util.*

/**
 * Description:这样写的好处是以后需要用，直接用即可，不需要要在相应的类中
 * 定义静态方法，而且也易于管理和操作
 * Date：2019/7/4-15:57
 * Author: cwh
 */
object ImageAdapter {

    @JvmStatic
    @BindingAdapter("url")
    fun loadImage(image: ImageView, url: String) {
        Glide.with(image.context).load(url).into(image)
        Toast.makeText(image.context, "Url", Toast.LENGTH_SHORT).show()
    }

    @JvmStatic
    @BindingAdapter("toast")
    fun toast(image: ImageView, text: String) {
        Toast.makeText(image.context, text, Toast.LENGTH_SHORT).show()
    }

    @JvmStatic
    @BindingAdapter("android:text")
    fun btnText(btn: Button, text: String) {
        btn.text = "$text ${Random().nextInt(18)}"
    }
}