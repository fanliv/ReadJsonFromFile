package com.cyborg.readingfile.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cyborg.readingfile.databinding.CityItemLayoutBinding
import com.cyborg.readingfile.domain.DomainCity

class CityAdapter(private val onClickListener: OnClickListener): ListAdapter<DomainCity, CityAdapter.CityViewHolder>(DiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(CityItemLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(city)
        }
        holder.bind(city)
    }

    class CityViewHolder(private val binding: CityItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(city: DomainCity){
            binding.city = city
            binding.executePendingBindings()
        }
    }
    companion object DiffCallBack: DiffUtil.ItemCallback<DomainCity>() {
        override fun areItemsTheSame(oldItem: DomainCity, newItem: DomainCity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DomainCity, newItem: DomainCity): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class OnClickListener(val clickListener: (city: DomainCity)->Unit){
        fun onClick(city: DomainCity) = clickListener(city)
    }

}