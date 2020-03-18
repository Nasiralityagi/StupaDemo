package com.android.stupa.core.auth.data.repo

import com.android.stupa.core.auth.data.model.LoginResponse
import com.android.stupa.core.auth.data.model.SignupResponse
import com.android.stupa.store.PrefStoreManager

class AuthRepository {

    suspend fun userSignUp(data: SignupResponse) {
        saveDataUserSignup(data)
    }

    fun saveDataUserSignup(it: SignupResponse) {
        PrefStoreManager.put(it, it.email)
    }

    suspend fun userLogin(data: LoginResponse) {
        saveDataUserLogin(data)
    }

    fun saveDataUserLogin(it: LoginResponse) {
        //PrefStoreManager.put(it, PrefStoreManager.LOGIN)
    }

    fun getAllUsers(): Map<String, *> {
        return PrefStoreManager.readAll()
    }

    companion object {
        fun getInstance(): AuthRepository {
            val mInstance: AuthRepository by lazy { AuthRepository() }
            return mInstance
        }
    }
}