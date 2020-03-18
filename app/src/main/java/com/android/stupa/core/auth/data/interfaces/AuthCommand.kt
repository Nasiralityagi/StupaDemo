package com.android.stupa.core.auth.data.interfaces

interface AuthCommand {
    fun onSuccess()
    fun onFailure(message: String)
    fun openActivity()
}