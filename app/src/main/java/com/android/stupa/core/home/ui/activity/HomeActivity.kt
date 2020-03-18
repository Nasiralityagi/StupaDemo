package com.android.stupa.core.home.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.android.stupa.core.home.data.viewmodel.HomeActivityViewModel
import kotlinx.android.synthetic.main.activity_home.*
import androidx.navigation.ActivityNavigator
import com.android.stupa.R
import com.android.stupa.core.factory.StupaModelFactory
import com.android.stupa.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeActivityViewModel
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewModel = ViewModelProvider(this, StupaModelFactory()).get(HomeActivityViewModel::class.java)
        binding.viewModel = viewModel
        setSupportActionBar(toolbar)
    }


    override fun finish() {
        super.finish()
        ActivityNavigator.applyPopAnimationsToPendingTransition(this)
    }
}

