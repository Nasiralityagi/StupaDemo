package com.android.stupa.utills

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun Context.toast(message: String, isLong: Boolean = false) {
    Toast.makeText(this, message, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}

fun View.toast(message: String, isLong: Boolean = false) {
    Toast.makeText(this.context, message, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT)
        .show()
}

fun <T> Context.invokeActivity(activity: Class<T>) {
    Intent(this, activity).also {
        this.startActivity(it)
    }
}





