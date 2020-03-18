package com.android.stupa.core.home.data.repo


class HomeActivityRepository {

    companion object {
        fun getInstance(): HomeActivityRepository {
            val mInstance: HomeActivityRepository by lazy { HomeActivityRepository() }
            return mInstance
        }
    }
}