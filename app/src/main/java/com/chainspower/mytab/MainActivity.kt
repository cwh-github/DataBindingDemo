package com.chainspower.mytab

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import com.google.android.material.tabs.TabLayout
import android.text.Html
import android.text.Spanned
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTvContent.text =
            fromHtml("<p>尊敬的GMW用户：</p>\\n<p>&nbsp; &nbsp; &nbsp;您好！</p>\\n<p>&nbsp; &nbsp; &nbsp;目前GMW处于内测阶段，为了不影响用户体验，我们正在积极的进行技术升级与维护。</p>")
        mTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                val position = p0!!.position
                Log.d("Position", "position is $position")
                Toast.makeText(this@MainActivity, "Select position is :$position", Toast.LENGTH_SHORT).show()
            }

        })
        for (i in 1..4) {
            val tabView = LayoutInflater.from(this).inflate(R.layout.tab_item, null)
            val textView = tabView.findViewById<TextView>(R.id.mTvBottom)
            textView.text = "Home"
            val drawable = resources.getDrawable(R.drawable.home)
            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
            textView.setCompoundDrawables(null, drawable, null, null)
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(tabView))
        }

        mBtnKTX.setOnClickListener {

            startActivity(Intent(this@MainActivity, KTXCoreStudyActivity::class.java))
        }

        mBtnArgb.setOnClickListener {
            startActivity(Intent(this@MainActivity, ArgbEvaluatorActivity::class.java))
        }

        mBtnMotion.setOnClickListener {
            startActivity(Intent(this@MainActivity, MotionLayoutActivity::class.java))
        }

        mBtnConstrain.setOnClickListener {
            startActivity(Intent(this@MainActivity, ConstrainActivity::class.java))
        }

        mBtnDialogFragment.setOnClickListener {
            startActivity(Intent(this@MainActivity, DialogFragmentActivity::class.java))
        }

        mRelaParent.setOnClickListener {

        }

        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
        getExternalFilesDir(Environment.DIRECTORY_MUSIC)
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        //activityManager.clearApplicationUserData()

//        val intent=Intent(this,MyService::class.java)
//        startService(intent)

        //setComponentEnabled()

        mBtnDataBinding.setOnClickListener {
            startActivity(Intent(this@MainActivity, DataBindingActivity::class.java))
        }

    }

    private fun setComponentEnabled() {
        val componentName = ComponentName(this, ConstrainActivity::class.java)
        val res = packageManager.getComponentEnabledSetting(componentName)
        if (res == PackageManager.COMPONENT_ENABLED_STATE_DEFAULT || res == PackageManager.COMPONENT_ENABLED_STATE_ENABLED) {
            packageManager.setComponentEnabledSetting(
                componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
        } else {
            packageManager.setComponentEnabledSetting(
                componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
            )
        }
    }

    private fun fromHtml(source: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(source)
        }
    }
}
