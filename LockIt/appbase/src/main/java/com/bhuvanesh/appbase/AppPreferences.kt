package com.bhuvanesh.appbase

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {

    private const val NAME = "LockerPref"
    private const val MODE = Context.MODE_PRIVATE

    private const val KEY_DOB = "Employee_Dob"

    private lateinit var preferences: SharedPreferences

    fun getInstance(context: Context) = context.getSharedPreferences(NAME, MODE)

    /**
     * SharedPreferences extension function, so we won't need to call edit() and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    fun SharedPreferences.Editor.put(pair: Pair<String, Any>) {
        val key = pair.first
        val value = pair.second
        when(value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            else -> error("Only primitive types can be stored in SharedPreferences")
        }
    }

    /*
    * ******* MEMBER EXTENSION FUNCTION *******
    * Extension functions / properties are usually defined on package level so that they can be imported and accessed from anywhere else with ease.
    * It's also possible to define these on class or object level, which is then called a "member extension function / preoperties".
    * These kinds of extension functions can easily be used inside that class but not from outside.
    *
    * In order to make them accessible from anywhere outside the enclosing class, that class has to be brought "into scope".
    * The with function is very useful here:
    * */
    // Extension property must have accessors or to be abstract
    var SharedPreferences.myDob : Long
        get() = getLong(KEY_DOB, 0)
        set(value) {
            editMe {
                it.put(KEY_DOB to value)
            }
        }
}