package com.android.stupa.core.home.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.stupa.core.auth.data.model.SignupResponse
import com.android.stupa.databinding.HomeListItemBinding
import java.util.ArrayList

class CategoryAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mCategoryList = ArrayList<SignupResponse>()

    fun setAppList(categoryModel: ArrayList<SignupResponse>) {
        mCategoryList.addAll(categoryModel)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        Log.d("LIST_SIZE","" + mCategoryList.size)
        return mCategoryList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val feeder = mCategoryList[position]
        (holder as RecyclerViewHolder).bind(feeder)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val applicationBinding =  HomeListItemBinding.inflate(layoutInflater, parent, false)
        return RecyclerViewHolder(applicationBinding)
    }

    inner class RecyclerViewHolder(
            private var applicationBinding: HomeListItemBinding
    ) : RecyclerView.ViewHolder(applicationBinding.root), View.OnClickListener {

        fun bind(feed: SignupResponse) {
            applicationBinding.listModel  = feed
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
          //TODO implement listener on item click
        }

    }
}