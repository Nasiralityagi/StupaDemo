package com.android.stupa.core.auth.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.android.stupa.R
import com.android.stupa.core.auth.data.interfaces.AuthCommand
import com.android.stupa.core.auth.data.viewmodel.LoginViewModel
import com.android.stupa.core.factory.StupaModelFactory
import com.android.stupa.core.home.ui.activity.HomeActivity
import com.android.stupa.databinding.ActivityLoginBinding
import com.android.stupa.utills.Helper
import com.android.stupa.utills.invokeActivity
import com.android.stupa.utills.toast

class LoginActivity : AppCompatActivity(), AuthCommand {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Helper.setFullScreen(this, window)
        setupBindings(savedInstanceState)
    }

    private fun setupBindings(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
            viewModel = ViewModelProvider(this@LoginActivity, StupaModelFactory()).get(LoginViewModel::class.java)
            binding.viewModel = viewModel
            registerObservables()
        }
    }

    /**With this No observer needed in activity*/
    private fun registerObservables() {
        viewModel.navigationEvent.setEventReceiver(this, this)
    }

    override fun onSuccess() {
        this@LoginActivity.invokeActivity(HomeActivity::class.java)
        finish()
    }

    override fun onFailure(message: String) {
        this.toast(message)
    }

    override fun openActivity() {
        this@LoginActivity.invokeActivity(SignupActivity::class.java)
        finish()
    }

}


