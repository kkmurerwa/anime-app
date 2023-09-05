package com.murerwa.animeapp.features.search.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.murerwa.animeapp.databinding.ListItemSearchResultBinding
import com.murerwa.animeapp.features.search.domain.models.SearchResult

class ImageSearchResultsAdapter(
    private val results: List<SearchResult>
): Adapter<ImageSearchResultsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemSearchResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentResult = results[position]

        holder.bindView(currentResult)
    }

    inner class ViewHolder(private val binding: ListItemSearchResultBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(result: SearchResult) {
            binding.apply {
                textViewFileName.text = result.filename
                textViewSimilarity.text = result.similarity.toString()
            }
        }
    }
}