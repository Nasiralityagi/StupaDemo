package com.android.stupa.core.home.data.repo

class ProfileFragmentRepository {

    companion object {
        fun getInstance(): ProfileFragmentRepository {
            val mInstance: ProfileFragmentRepository by lazy { ProfileFragmentRepository() }
            return mInstance
        }
    }
}