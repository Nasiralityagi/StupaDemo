package com.android.stupa

import android.app.Application
import com.android.stupa.store.PrefStoreManager

class Stupa :  Application() {
    init {
        mInstance = this
    }
    companion object{
        private lateinit var mInstance: Stupa
        @Synchronized
        fun getInstance(): Stupa {
            return mInstance as Stupa
        }
    }

    override fun onCreate() {
        super.onCreate()
        PrefStoreManager.with(this)
    }
}