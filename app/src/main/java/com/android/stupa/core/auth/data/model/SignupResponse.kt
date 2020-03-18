package com.android.stupa.core.auth.data.model

data class SignupResponse(
    val status : Int,
    val name : String,
    val email : String,
    val phone : String,
    val password : String,
    val city : String,
    val gender : String
)