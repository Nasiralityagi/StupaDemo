package com.android.stupa.core.auth.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.android.stupa.R
import com.android.stupa.core.auth.data.interfaces.AuthCommand
import com.android.stupa.core.auth.data.viewmodel.SignupViewModel
import com.android.stupa.core.factory.StupaModelFactory
import com.android.stupa.core.home.ui.activity.HomeActivity
import com.android.stupa.databinding.ActivitySignupBinding
import com.android.stupa.utills.Helper
import com.android.stupa.utills.invokeActivity
import com.android.stupa.utills.toast
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity(), AuthCommand {

    private var cityCode = arrayOf("NewDelhi", "Gurugram", "Noida", "Ghaziabad", "Chandigarh", "Hydrabad", "Pune", "Bengluru", "Kolkatta","Mumbai","Goa","Agra")

    private lateinit var viewModel: SignupViewModel
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Helper.setFullScreen(this, window)


        setupBindings(savedInstanceState)

        setupSpinner()
    }

    private fun setupSpinner() {
        val adapterCity = ArrayAdapter(this, R.layout.tv_spinner, cityCode)
        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        city_spinner.adapter = adapterCity
    }

    private fun setupBindings(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
            viewModel = ViewModelProvider(this@SignupActivity, StupaModelFactory()).get(SignupViewModel::class.java)
            binding.viewModel = viewModel
            registerObservables()
        }
    }

    /**With this No observer needed in activity*/
    private fun registerObservables() {
        viewModel.navigationEvent.setEventReceiver(this, this)
    }


    override fun onSuccess() {
        this@SignupActivity.invokeActivity(HomeActivity::class.java)
        finish()
    }

    override fun onFailure(message: String) {
        this.toast(message)
    }

    override fun openActivity() {
        this@SignupActivity.invokeActivity(LoginActivity::class.java)
        finish()
    }

}
