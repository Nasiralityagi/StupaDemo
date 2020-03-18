package com.android.stupa.core.auth.data.viewmodel

import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.android.stupa.core.auth.data.interfaces.AuthCommand
import com.android.stupa.core.auth.data.model.LoginResponse
import com.android.stupa.core.auth.data.model.SignupResponse
import com.android.stupa.core.auth.data.repo.AuthRepository
import com.android.stupa.liveobserver.LiveMessageEvent
import com.android.stupa.utills.Coroutines
import com.android.stupa.utills.Helper
import com.google.gson.Gson
import java.lang.Exception

class LoginViewModel(private val repository: AuthRepository) : ViewModel() {

    var navigationEvent: LiveMessageEvent<AuthCommand> = LiveMessageEvent()

    var emailid: ObservableField<String> = ObservableField("")
    var password: ObservableField<String> = ObservableField("")
    var progress: ObservableInt = ObservableInt(View.GONE)
    //val incognitoUser: AuthResponse = repository.getDataForIncognitoUser()


    fun onLoginButtonClick() {
        val emailid = emailid.get()!!
        val password = password.get()!!

        if(TextUtils.isEmpty(emailid) || !isValidEmail(emailid) ){
            navigationEvent.sendEvent{ onFailure("Valid Email-Id filed is required!") }
            return
        }else if(TextUtils.isEmpty(password) || !isPassValid(password)){
            navigationEvent.sendEvent{ onFailure("Valid Password filed is invalid, min length 8!")}
            return
        }

        progress.set(View.VISIBLE)
        Coroutines.main {
            try {
                progress.set(View.VISIBLE)
                val data = LoginResponse(
                    status = 1,
                    email = emailid,
                    password = password
                )

                val entries = repository.getAllUsers()
                if(entries.isEmpty()){
                    progress.set(View.GONE)
                    navigationEvent.sendEvent { onFailure("User Not found!") }
                    return@main
                }
                var checkEmailExist: Boolean = false
                for ((key, value) in entries) {
                    if(emailid.equals(key)){
                        checkEmailExist = true
                    }
                    Log.d("map values", key + ": " + value.toString())
                }

                if(!checkEmailExist) {
                    progress.set(View.GONE)
                    navigationEvent.sendEvent { onFailure("User Not found!") }
                    return@main
                }

                repository.userLogin(data)
                progress.set(View.GONE)
                //success
                navigationEvent.sendEvent { onSuccess() }
            }catch (e: Exception){
                //failed
                navigationEvent.sendEvent { onFailure("Failed!") }
                e.printStackTrace()
            }
        }
    }

    private fun isValidEmail(email: CharSequence): Boolean {
        /*Min a@b.cc*/
        var isValid = false
        if (email.isEmpty()) {
            isValid = false
        } else if (Helper.isEmailValid(email)) {
            isValid = true
        }
        return isValid
    }

    private fun isPassValid(password: CharSequence): Boolean {
        var isValid = false
        if (password.isEmpty()) {
            isValid = false
        } else if (password.length > 7) {
            isValid = true
        }
        return isValid
    }

    fun openSignup() {
      navigationEvent.sendEvent { openActivity() }
    }
}
