package com.android.stupa.core.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.stupa.core.auth.data.repo.AuthRepository
import com.android.stupa.core.auth.data.viewmodel.LoginViewModel
import com.android.stupa.core.auth.data.viewmodel.SignupViewModel
import com.android.stupa.core.splash.data.viewmodel.SplashViewModel
import com.android.stupa.core.home.data.repo.HomeActivityRepository
import com.android.stupa.core.home.data.repo.HomeFragmentListRepository
import com.android.stupa.core.home.data.viewmodel.HomeActivityViewModel
import com.android.stupa.core.home.data.viewmodel.HomeFragmentViewModel

@Suppress("UNCHECKED_CAST")
class StupaModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            SplashViewModel() as T
        } else return if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            LoginViewModel(AuthRepository.getInstance()) as T
        } else return if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            SignupViewModel(AuthRepository.getInstance()) as T
        } else return if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            LoginViewModel(AuthRepository.getInstance()) as T
        }else return if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            SignupViewModel(AuthRepository.getInstance()) as T
        } else return if (modelClass.isAssignableFrom(HomeActivityViewModel::class.java)) {
            HomeActivityViewModel(HomeActivityRepository.getInstance()) as T
        } else return if (modelClass.isAssignableFrom(HomeFragmentViewModel::class.java)) {
            HomeFragmentViewModel(HomeFragmentListRepository.getInstance()) as T
        }else {
            //TODO for others view model repository
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}