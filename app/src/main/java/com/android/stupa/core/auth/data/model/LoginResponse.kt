package com.android.stupa.core.auth.data.model

data class LoginResponse(
    val status : Int,
    val email : String,
    val password : String
)