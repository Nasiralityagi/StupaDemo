package com.android.stupa.core.home.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.stupa.R
import com.android.stupa.core.auth.data.model.SignupResponse
import com.android.stupa.core.factory.StupaModelFactory
import com.android.stupa.core.home.data.viewmodel.HomeFragmentViewModel
import com.android.stupa.core.home.ui.adapters.CategoryAdapter
import com.android.stupa.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeFragmentViewModel
    private lateinit var binding: FragmentHomeBinding
    private var dataList: ArrayList<SignupResponse> = ArrayList()
    private lateinit var categoryAdapter: CategoryAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        viewModel = ViewModelProvider(this@HomeFragment, StupaModelFactory()).get(HomeFragmentViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView(dataList)   //send empty list initially

        subscribeDataCallBack()
    }

    private fun subscribeDataCallBack() {
        viewModel.getProjectList()?.observe(viewLifecycleOwner, Observer<ArrayList<SignupResponse>> {
            if (it != null) {
                categoryAdapter.setAppList(it)
            }
        })
    }

    private fun setRecyclerView(dataList: ArrayList<SignupResponse>) {
        categoryAdapter = CategoryAdapter()
        val categoryLinearLayoutManager = LinearLayoutManager(context)
        categoryLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.root.recycler_view.layoutManager = categoryLinearLayoutManager
        categoryAdapter.setAppList(dataList)
        binding.root.recycler_view.adapter = categoryAdapter
    }

}
