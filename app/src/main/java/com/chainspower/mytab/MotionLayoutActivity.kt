package com.chainspower.mytab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Transition
import android.util.Log
import android.widget.SeekBar
import androidx.constraintlayout.motion.widget.MotionLayout
import kotlinx.android.synthetic.main.activity_motion_layout.*

class MotionLayoutActivity : AppCompatActivity() {
    private val TAG="MOTION"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_layout)

        mBtnStart.setOnClickListener {

            mMotion.transitionToStart()
        }

        mBtnEnd.setOnClickListener {
            mMotion.transitionToEnd()
        }

        mSeeKBar.min=0
        mSeeKBar.max=100
        mSeeKBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mMotion.progress=progress*0.01f
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
//
//        mMotion.setTransitionListener(object :MotionLayout.TransitionListener{
//            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
//            }
//
//            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
//            }
//
//            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
//                Log.d(TAG,"params is p1 $p1 , p2 $p2,p3 $p3")
//                mSeeKBar.progress=(p3*100).toInt()
//            }
//
//            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
//            }
//
//        })

//        mImg1.setOnClickListener {
//
//        }
    }
}
