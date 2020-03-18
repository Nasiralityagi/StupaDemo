package com.android.stupa.core.home.data.repo

import com.android.stupa.store.PrefStoreManager

class HomeFragmentListRepository {

    suspend fun fetchUsersData(): Map<String, *> {
        return PrefStoreManager.readAll()
    }

    companion object {
        fun getInstance(): HomeFragmentListRepository {
            val mInstance: HomeFragmentListRepository by lazy { HomeFragmentListRepository() }
            return mInstance
        }
    }
}