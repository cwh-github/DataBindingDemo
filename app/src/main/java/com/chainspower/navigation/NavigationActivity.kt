package com.chainspower.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import com.chainspower.mytab.R

/**
 * Navigation 一个Activity,多个Fragment的实现方式
 */
class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(this, R.id.navi_main).navigateUp()
    }
}
