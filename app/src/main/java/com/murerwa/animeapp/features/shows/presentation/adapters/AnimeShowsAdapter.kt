package com.murerwa.animeapp.features.shows.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.murerwa.animeapp.R
import com.murerwa.animeapp.core.utils.loadImage
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

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentShow = items[position]
        holder.bindView(currentShow)
    }

    inner class ViewHolder(private val binding: ListItemShowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(show: Show) {
            binding.apply {
                textViewTitle.text = show.title
                textViewRank.text = show.rank.toString()
                textViewRating.text = String.format("%.1f", show.score * 10) + "% rating"
                textViewRating.text = binding.root.context.getString(
                    R.string.template_percentage,
                    String.format("%.1f", show.score * 10),
                    "rating"
                )
                textViewYear.text = if (show.year != 0) show.year.toString() else "unknown"

                imageViewShow.loadImage(
                    show.images.jpg.largeImageUrl
                )
            }
        }
    }
}