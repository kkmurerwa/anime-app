package com.murerwa.animeapp.features.shows.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.murerwa.animeapp.databinding.ListItemShowBinding
import com.murerwa.animeapp.features.shows.domain.models.Show

class AnimeShowsAdapter(
    private val items: List<Show>
): Adapter<AnimeShowsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemShowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

//    override fun getItemCount(): Int = items.size
    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val currentShow = items[position]
        holder.bindView()
    }

    inner class ViewHolder(private val binding: ListItemShowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView() {

        }
    }
}