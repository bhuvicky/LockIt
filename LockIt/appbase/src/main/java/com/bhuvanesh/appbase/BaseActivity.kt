package com.bhuvanesh.appbase

import androidx.appcompat.app.AppCompatActivity

class BaseActivity: AppCompatActivity() {

    private val fm = supportFragmentManager

    fun replace(containerId: Int, fragment: BaseFragment) {
        with(fm.beginTransaction()) {
            replace(containerId, fragment)
            addToBackStack(null)
            commit()
        }
    }

    fun setTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun onBackPressed() {
        if (fm.backStackEntryCount > 1) {
            fm.popBackStack()
        } else {
            finish()
        }
    }
}