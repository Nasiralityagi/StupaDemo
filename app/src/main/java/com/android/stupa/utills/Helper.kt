package com.android.stupa.utills

import android.content.Context
import android.os.Build
import android.view.View
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import java.util.regex.Pattern

class Helper {
    companion object {
        fun setFullScreen(context: Context, window: Window) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val decorView = window.decorView
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LOW_PROFILE or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                decorView.setOnApplyWindowInsetsListener { v, insets ->
                    val defaultInsets = v.onApplyWindowInsets(insets)
                    defaultInsets.replaceSystemWindowInsets(
                        defaultInsets.systemWindowInsetLeft,
                        0,
                        defaultInsets.systemWindowInsetRight,
                        defaultInsets.systemWindowInsetBottom
                    )
                }
                ViewCompat.requestApplyInsets(decorView)
                //Make the status bar transparent, if you don't want to be transparent, you can set a different color
                window.statusBarColor = ContextCompat.getColor(context, android.R.color.transparent)
                window.navigationBarColor =
                    (ContextCompat.getColor(context, android.R.color.transparent))
            }
        }

        /**Check email validation*/
        fun isEmailValid(email: CharSequence): Boolean {
            val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
            val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(email)
            return matcher.matches()
        }
    }
}