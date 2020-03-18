package com.android.stupa.core.splash.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.android.stupa.R
import com.android.stupa.core.auth.ui.LoginActivity
import com.android.stupa.core.auth.ui.SignupActivity
import com.android.stupa.core.factory.StupaModelFactory
import com.android.stupa.core.splash.data.interfaces.SplashCommand
import com.android.stupa.core.splash.data.viewmodel.SplashViewModel
import com.android.stupa.databinding.ActivitySplashBinding
import com.android.stupa.utills.invokeActivity

class Splash: AppCompatActivity(), SplashCommand {

    private  lateinit var  binding: ActivitySplashBinding
    private  lateinit var  viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBindings(savedInstanceState)
    }

    private fun setupBindings(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
            viewModel = ViewModelProvider(this@Splash, StupaModelFactory()).get(SplashViewModel::class.java)
            binding.viewModel = viewModel
            registerObservables()
        }
    }

    /**With this No observer needed in activity*/
    private fun registerObservables() {
        viewModel.navigationEvent.setEventReceiver(this, this)
    }

    override fun startLoginActivity() {
        this.invokeActivity(LoginActivity::class.java)
        //finish()
    }

    override fun startSignUpActivity() {
        this.invokeActivity(SignupActivity::class.java)
        //finish()
    }
}
