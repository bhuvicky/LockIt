package com.bhuvanesh.appbase

import android.content.Context
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders


fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun showViews(vararg views: View) {
    // TODO: Can use forEach() instead of map ??
    views.map { it.show() }
}

fun goneViews(vararg views: View) {
    views.forEach { it.gone() }
}

fun <ViewT : View> AppCompatActivity.bindView(@IdRes idRes: Int): Lazy<ViewT> {
    return lazy(LazyThreadSafetyMode.NONE) {
        findViewById<ViewT>(idRes)
    }
}

inline fun Context.checkInternetAndExecute(function: () -> Unit) {
    /*if (MobileApplication.isNetConnected(this)) {
        function()
    } else {
        Toast.makeText(this, getString(R.string.msg_no_internet), Toast.LENGTH_SHORT).show()
    }*/
}


/*
 This extension function is used to check whether the ViewModel obj should create with constructor or without constructor
 if with constructor means, our custom ViewModelFactory is used to create ViewModel object.
 if without constructor means, default ViewModelFactory will used to create ViewModel object.

 inline parameter - val create: (() -> T)? = null;  should not be nullable type   or
 make it "noiniline"
 */
inline fun <reified T : ViewModel> Fragment.getViewModel(noinline create: (() -> T)? = null): T {
    return create?.let {
        ViewModelProviders.of(this, AppViewModelFactory(create)).get(T::class.java)
    } ?: ViewModelProviders.of(this).get(T::class.java)

}