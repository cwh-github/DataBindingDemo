package com.chainspower.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.SeekBar
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

/**
 * Description:
 * Date：2019/7/4-19:43
 * Author: cwh
 */
class MySeekBar(context: Context?, attrs: AttributeSet?) : SeekBar(context, attrs) {

    init {
        this.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                listener?.onChange()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

    }

    companion object {

        var listener: InverseBindingListener? = null

        @BindingAdapter("progressBind")
        @JvmStatic
        fun setProgressBind(seekBar: MySeekBar, progress: Int) {
            if (getPorgressBind(seekBar) != progress) {
                seekBar.progress = progress
            }

        }

        @InverseBindingAdapter(attribute = "progressBind", event = "event_progress")
        @JvmStatic
        fun getPorgressBind(seekBar: MySeekBar): Int {
            return seekBar.progress
        }

        @BindingAdapter("event_progress")
        @JvmStatic
        fun onProgressChange(seekBar: MySeekBar, listener: InverseBindingListener) {
            Companion.listener = listener
        }
    }

}