package com.chainspower.mytab

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.text.format.DateUtils
import android.text.method.DigitsKeyListener
import android.text.method.PasswordTransformationMethod
import android.text.util.Linkify
import android.util.AtomicFile
import android.util.Log
import android.view.ViewConfiguration
import android.view.animation.Animation
import androidx.collection.LruCache
import androidx.core.animation.*
import androidx.core.content.ContentResolverCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_ktxcore_study.*

class KTXCoreStudyActivity : AppCompatActivity() {

    private val TAG = "KTX"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ktxcore_study)
        animation()

        initView()
    }

    private fun initView() {
        Linkify.addLinks(mTvLink,Linkify.WEB_URLS)
    }

    private fun animation() {
        val animation = ObjectAnimator.ofFloat(mView, "translationX", 0f, 240f)

        mBtnAnimation.setOnClickListener {
            animation.duration = 1200
            animation.repeatMode = ValueAnimator.REVERSE
            animation.repeatCount = 2
            animation.start()
        }

        animation.addListener({
            Log.d(TAG, "onEnd")
        }, {
            Log.d(TAG, "onStart")
        }, {
            Log.d(TAG, "onCancel")
        }, {
            Log.d(TAG, "onRepeat")
        })


        animation.addPauseListener {
            Log.d(TAG, "add Pause")
        }

        animation.doOnResume {
            Log.d(TAG, "do Resume")
        }

        animation.doOnPause {
            Log.d(TAG, "do Pause")
        }

        animation.doOnCancel {
            Log.d(TAG, "do Cancel")
        }

        animation.doOnEnd {
            Log.d(TAG, "do End")
        }

        animation.doOnStart {
            Log.d(TAG, "do Start")
        }

        animation.doOnRepeat {
            Log.d(TAG, "do Repeat")
        }


        val location=IntArray(2)
        mView.getLocationInWindow(location)
        Log.d(TAG,"Location in window is x: ${location[0]}  y: ${location[1]}")

        val loc=IntArray(2)
        mView.getLocationOnScreen(loc)
        Log.d(TAG,"Location in Screen is x: ${loc[0]}  y: ${loc[1]}")

        mTvText.text="12345678"
        mTvText.transformationMethod=PasswordTransformationMethod.getInstance()

        mTvText.error="ERROR"

        val result=PhoneNumberUtils.convertKeypadLettersToDigits("asjdhasj")
        Log.d(TAG,"Transfer num is : $result")


        mEdText.keyListener = DigitsKeyListener.getInstance("0123456aswe")

        val resultDate = DateUtils.formatDateTime(this,System.currentTimeMillis(),DateUtils.FORMAT_NO_MONTH_DAY )
        Log.d(TAG,"Date is $resultDate")
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Log.d(TAG, "Width is ${mView.width} Hight is ${mView.height}")

        val location=IntArray(2)
        mView.getLocationInWindow(location)
        Log.d(TAG,"Location in window is x: ${location[0]}  y: ${location[1]}")

        val loc=IntArray(2)
        mView.getLocationOnScreen(loc)
        Log.d(TAG,"Location in Screen is x: ${loc[0]}  y: ${loc[1]}")
    }


}
