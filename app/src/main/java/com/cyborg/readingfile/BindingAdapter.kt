package com.cyborg.readingfile

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.cyborg.readingfile.domain.DomainCity
import com.cyborg.readingfile.ui.main.CityAdapter

@BindingAdapter("cityList")
fun bindRecycleView(recyclerView: RecyclerView, data: List<DomainCity>?){
    data?.let {
        val adapter = recyclerView.adapter as CityAdapter
        adapter.submitList(data)
    }

}