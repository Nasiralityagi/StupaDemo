package com.android.stupa.core.home.data.viewmodel

import android.util.Log
import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.stupa.core.auth.data.model.SignupResponse
import com.android.stupa.core.home.data.repo.HomeFragmentListRepository
import com.android.stupa.utills.Coroutines
import com.google.gson.Gson

class HomeFragmentViewModel(private val repository: HomeFragmentListRepository) : ViewModel() {

    private var dataList: ArrayList<SignupResponse> = ArrayList()
    private val mutablePostList: MutableLiveData<ArrayList<SignupResponse>> = MutableLiveData()
    var progress: ObservableInt = ObservableInt(View.GONE)


    fun getProjectList(): LiveData<ArrayList<SignupResponse>>? {
        progress.set(View.VISIBLE)
        Coroutines.main {
            try {
                val entries = repository.fetchUsersData()
                for ((key, value) in entries) {
                    val user = Gson().fromJson(value.toString(), SignupResponse::class.java)
                    dataList.add(user)
                    Log.d("map values", key + ": " + value.toString())
                }

                entries.let {
                    mutablePostList.postValue(dataList)//notify observable
                    progress.set(View.GONE)
                    return@main
                }
            } catch (e: Exception) {
                progress.set(View.GONE)
                e.printStackTrace()
            }
        }

        return mutablePostList
    }
}