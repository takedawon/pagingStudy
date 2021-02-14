package com.lanic.pagingstudy.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lanic.pagingstudy.data.response.PokeiResponse
import com.lanic.pagingstudy.databinding.CustomPokeiViewBinding

class MainAdapter : ListAdapter<PokeiResponse.Result, PokeiViewHolder>(
    object : DiffUtil.ItemCallback<PokeiResponse.Result>() {
        override fun areItemsTheSame(
            oldItem: PokeiResponse.Result,
            newItem: PokeiResponse.Result
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: PokeiResponse.Result,
            newItem: PokeiResponse.Result
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeiViewHolder {
        return PokeiViewHolder(
            CustomPokeiViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PokeiViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PokeiViewHolder(private val binding: CustomPokeiViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: PokeiResponse.Result) {
        binding.item = item
    }
}