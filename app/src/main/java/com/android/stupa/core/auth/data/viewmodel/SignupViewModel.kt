package com.android.stupa.core.auth.data.viewmodel

import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.RadioGroup
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.android.stupa.R
import com.android.stupa.core.auth.data.interfaces.AuthCommand
import com.android.stupa.core.auth.data.model.SignupResponse
import com.android.stupa.core.auth.data.repo.AuthRepository
import com.android.stupa.liveobserver.LiveMessageEvent
import com.android.stupa.utills.Coroutines
import com.android.stupa.utills.Helper
import com.google.gson.Gson
import java.lang.Exception

class SignupViewModel(private val repository: AuthRepository) : ViewModel() {

  var navigationEvent: LiveMessageEvent<AuthCommand> = LiveMessageEvent()

  var name: ObservableField<String> = ObservableField("")
  var emailid: ObservableField<String> = ObservableField("")
  var mobileno: ObservableField<String> = ObservableField("")
  var password: ObservableField<String> = ObservableField("")
  var city: ObservableField<String> = ObservableField("")
  var gender: ObservableField<String> = ObservableField("Male")
  var progress: ObservableInt = ObservableInt(View.GONE)

    //val publicUser: AuthResponse = repository.getDataForPublicUser()

    fun onSelectItem(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
           city.set(parent!!.selectedItem.toString())
    }

    fun onCheckedChanged(radioGroup: RadioGroup, checkedId: Int) {
        if (checkedId == R.id.male) {
            gender.set("Male")
        } else  if (checkedId == R.id.female) {
            gender.set("Female")
        } else  if (checkedId == R.id.other) {
            gender.set("Other")
        }
    }

    fun onSignUpButtonClick() {
        val name = name.get()!!
        val emailid = emailid.get()!!
        val mobileno = mobileno.get()!!
        val password = password.get()!!
        val city = city.get()!!
        val gender = gender.get()!!

        if(TextUtils.isEmpty(this.name.get()!!)){
            navigationEvent.sendEvent{ onFailure("Name filed is required!") }
            return
        }else if(TextUtils.isEmpty(emailid) || !isValidEmail(emailid) ){
            navigationEvent.sendEvent{ onFailure("Email-Id filed is required!") }
            return
        }else if(TextUtils.isEmpty(mobileno)){
            navigationEvent.sendEvent{ onFailure("Mobile number is required!")}
            return
        }else if(TextUtils.isEmpty(password) || !isPassValid(password)){
            navigationEvent.sendEvent{ onFailure("Password filed is invalid, min length 8!")}
            return
        }else if(TextUtils.isEmpty(city)){
            navigationEvent.sendEvent{ onFailure("Select your city!")}
            return
        }else if(TextUtils.isEmpty(gender)){
            navigationEvent.sendEvent{ onFailure("Select your gender!")}
            return
        }

        Coroutines.main {
            try {
                progress.set(View.VISIBLE)
                val data = SignupResponse(
                    status = 1,
                    name = name,
                    email = emailid,
                    phone = mobileno,
                    password = password,
                    city = city,
                    gender = gender
                )
                repository.userSignUp(data)
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


    fun openLogin() {
      //If account is already exist
        navigationEvent.sendEvent{ openActivity() }
    }

    /*fun getAllData(){
       val entries = repository.getAllUsers()
        for ((key, value) in entries) {
            val topic = Gson().fromJson(value.toString(), SignupResponse::class.java)
         Log.d("map values", key + ": " + value.toString())
        }
    }*/
}