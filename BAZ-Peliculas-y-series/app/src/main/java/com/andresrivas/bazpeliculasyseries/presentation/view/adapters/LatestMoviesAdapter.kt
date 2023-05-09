package com.andresrivas.bazpeliculasyseries.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andresrivas.bazpeliculasyseries.databinding.ItemMovieBinding
import com.andresrivas.bazpeliculasyseries.domain.model.LatestMoviesModel
import com.andresrivas.bazpeliculasyseries.utilities.Constants
import com.andresrivas.bazpeliculasyseries.utilities.fromUrl

class LatestMoviesAdapter(
    private var onMovieItemClickListener: OnMovieItemClickListener? = null,
) : ListAdapter<LatestMoviesModel, LatestMoviesAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<LatestMoviesModel>() {
        override fun areItemsTheSame(
            oldItem: LatestMoviesModel,
            newItem: LatestMoviesModel,
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: LatestMoviesModel,
            newItem: LatestMoviesModel,
        ): Boolean = oldItem == newItem
    },
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onMovieItemClickListener)
    }

    fun setOnMovieItemClickListener(listener: OnMovieItemClickListener) {
        onMovieItemClickListener = listener
    }

    inner class ViewHolder(
        private val binding: ItemMovieBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            result: LatestMoviesModel,
            onMovieItemClickListener: OnMovieItemClickListener? = null,
        ) {
            binding.apply {
                movieImageView.fromUrl(Constants.TheMovieDBPosterBaseURL.plus(result.posterPath))
                movieTitleLabel.text = result.title
                root.setOnClickListener { onMovieItemClickListener?.onItemClick(result) }
            }
        }
    }

    fun interface OnMovieItemClickListener {
        fun onItemClick(trendingMovieModel: LatestMoviesModel)
    }
}
