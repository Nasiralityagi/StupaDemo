package com.android.stupa.core.home.data.repo

class ProfileActivityRepository {

    companion object {
        fun getInstance(): ProfileActivityRepository {
            val mInstance: ProfileActivityRepository by lazy { ProfileActivityRepository() }
            return mInstance
        }
    }
}