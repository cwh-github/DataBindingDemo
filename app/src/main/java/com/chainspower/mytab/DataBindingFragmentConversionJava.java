package com.chainspower.mytab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingConversion;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import com.chainspower.mytab.databinding.DataBindingConversion;

import java.util.Random;

/**
 * Description:
 * Date：2019/6/11-15:22
 * Author: cwh
 */
public class DataBindingFragmentConversionJava extends Fragment {

    private DataBindingConversion mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_conversion, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.setFlag(true);
        mBinding.mTvTv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.setFlag(new Random().nextBoolean());
            }
        });
    }

    //此方法是全局方法，会改变所有的使用DataBinding的控件(而且蛋疼的是在kotlin中使用BindingConversion报错，无法编译通过，
    // 而在java中却无此问题)
    //@BindingConversion
    public static String mTvTextConversion(String text) {
        return text + " conversion";
    }


    //    lateinit var mBinding: DataBindingConversion
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_conversion, container, false)
//        return mBinding.root
//    }
//
//
//    companion object {
//
//        //对布局文件中所有以@{string}的进行转换
//
//        @BindingConversion
//        @JvmStatic
//        fun mTvTextConversion(text: String): String {
//            return "$text conversion"
//        }
//    }
}
