package com.chainspower.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.InverseBindingMethod;
import androidx.databinding.InverseBindingMethods;

/**
 * Description:
 * Date：2019/7/4-20:25
 * Author: cwh
 */
@InverseBindingMethods({@InverseBindingMethod(type = MySeekBar2Java.class,
        attribute = "progressChange", event = "changeProgress")})
@SuppressLint("AppCompatCustomView")
public class MySeekBar2Java extends SeekBar {
    private InverseBindingListener mListener;
    private int mProgress;
    public MySeekBar2Java(Context context) {
        super(context);
        init();
    }


    public MySeekBar2Java(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySeekBar2Java(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mProgress=progress;
                if (mListener != null) {
                    mListener.onChange();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public MySeekBar2Java(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setProgressChange(int progress) {
        if (getProgress() != progress) {
            mProgress=progress;
            super.setProgress(progress);
        }
    }

    public int getProgressChange() {
        return mProgress;
    }

    public void setChangeProgress(InverseBindingListener inverseBindingListener) {
        this.mListener = inverseBindingListener;
    }


}
