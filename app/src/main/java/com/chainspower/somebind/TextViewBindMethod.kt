package com.chainspower.somebind

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods

/**
 * Description:
 * Date：2019/7/4-16:25
 * Author: cwh
 */

//BindingMethods在java中使用会报不能在一个注解中出现另一个注解参数的注解，所以我这里改为了java
//@BindingMethods({
//    @BindingMethod(type = TextView::class, attribute = "toastText", method = "toastTextShow")
//})
class TextViewBindMethod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {

    fun toastTextShow(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}