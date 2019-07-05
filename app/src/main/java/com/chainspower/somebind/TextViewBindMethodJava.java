package com.chainspower.somebind;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;

/**
 * Description:
 * <p>
 * BindingMethods指明的是一系列方法，其中BindingMethod指明什么类的属性，attribute指明在
 * xml中声明的属性名，method指明在xml中声明的属性会调用的方法，其中xml对应的参数为调用
 * 该函数需要的参数
 * <p>
 * <p>
 * Date：2019/7/4-16:32
 * Author: cwh
 */

@BindingMethods({@BindingMethod(type = TextView.class, attribute = "toastText", method = "toastTextShow")})
@SuppressLint("AppCompatCustomView")
public class TextViewBindMethodJava extends TextView {
    public TextViewBindMethodJava(Context context) {
        super(context);
    }

    public TextViewBindMethodJava(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewBindMethodJava(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TextViewBindMethodJava(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void toastTextShow(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }
}
