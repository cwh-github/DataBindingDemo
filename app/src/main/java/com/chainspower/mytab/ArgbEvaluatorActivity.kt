package com.chainspower.mytab

import android.animation.ArgbEvaluator
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_argb_evaluator.*
import org.w3c.dom.Text

class ArgbEvaluatorActivity : AppCompatActivity() {

    val PAGE_ONE_COLOR by lazy {
        ContextCompat.getColor(this, R.color.colorPrimary)
    }

    val PAGE_TWO_COLOR by lazy {
        ContextCompat.getColor(this, R.color.colorAccent)
    }

    val PAGE_THREE_COLOR by lazy {
        ContextCompat.getColor(this, R.color.colorThree)
    }

    private val mItemPageList: MutableList<TextView> = ArrayList()

    private var mLastPositionOffsetPixels=0

    //是否为左滑 true 左滑 false 右滑
    private var isLeft=true

    private val TAG = "ARGB"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_argb_evaluator)

        initViewPager()
    }

    private fun initViewPager() {
        mItemPageList.add(createPageView(PAGE_ONE_COLOR))
        mItemPageList.add(createPageView(PAGE_TWO_COLOR))
        mItemPageList.add(createPageView(PAGE_THREE_COLOR))
        mToolBar.setBackgroundColor(PAGE_ONE_COLOR)
        mViewPager.adapter = object : PagerAdapter() {
            override fun isViewFromObject(view: View, `object`: Any): Boolean {
                return view === `object`
            }

            override fun getCount(): Int {
                return mItemPageList.size
            }

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                container.addView(mItemPageList[position])
                return mItemPageList[position]
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                container.removeView(mItemPageList[position])
            }
        }


        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if(this@ArgbEvaluatorActivity.mLastPositionOffsetPixels !=0){
                    isLeft=mLastPositionOffsetPixels<positionOffsetPixels
                }
                mLastPositionOffsetPixels=positionOffsetPixels
                setArgbColor(position, positionOffset)
                Log.d(TAG, " $position position offset Pixel  is $positionOffsetPixels")
            }

            override fun onPageSelected(position: Int) {
            }

        })
    }

    /**
     * 设置渐变色
     */
    private fun setArgbColor(position: Int, positionOffset: Float) {
        val argb = ArgbEvaluator()
        when (position) {
            0 -> {
                val color = argb.evaluate(positionOffset, PAGE_ONE_COLOR, PAGE_TWO_COLOR) as Int
                mToolBar.setBackgroundColor(color)
                setStatusBarColor(color)
            }

            1 -> {
                val color=argb.evaluate(positionOffset,PAGE_TWO_COLOR,PAGE_THREE_COLOR) as Int
                mToolBar.setBackgroundColor(color)
                setStatusBarColor(color)
            }

            2 -> {
                mToolBar.setBackgroundColor(PAGE_THREE_COLOR)
                setStatusBarColor(PAGE_THREE_COLOR)
            }

        }
    }

    private fun setStatusBarColor(color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = color
        }
    }


    private fun createPageView(color: Int): TextView {
        val textView = TextView(this)
        val params = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        textView.layoutParams = params
        textView.setBackgroundColor(color)
        return textView
    }
}
