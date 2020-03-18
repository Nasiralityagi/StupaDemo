package com.android.stupa.core.splash.data.viewmodel

import androidx.lifecycle.ViewModel
import com.android.stupa.core.splash.data.interfaces.SplashCommand
import com.android.stupa.liveobserver.LiveMessageEvent

class SplashViewModel() : ViewModel() {

    var navigationEvent: LiveMessageEvent<SplashCommand> = LiveMessageEvent()

    fun onLoginButtonClick() {
      navigationEvent.sendEvent { startLoginActivity() }
    }

    fun onSignUpButtonClick() {
        navigationEvent.sendEvent { startSignUpActivity() }
    }

}