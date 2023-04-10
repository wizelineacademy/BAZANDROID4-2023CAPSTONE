package com.andresrivas.bazpeliculasyseries.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andresrivas.bazpeliculasyseries.databinding.ItemMovieBinding
import com.andresrivas.bazpeliculasyseries.domain.model.MovieModel
import com.andresrivas.bazpeliculasyseries.utilities.Constants.Companion.TheMovieDBPosterBaseURL
import com.andresrivas.bazpeliculasyseries.utilities.fromUrl

class MovieAdapter(
    private var onMovieItemClickListener: OnMovieItemClickListener? = null
) : ListAdapter<MovieModel, MovieAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<MovieModel>() {
        override fun areItemsTheSame(
            oldItem: MovieModel,
            newItem: MovieModel
        ): Boolean = oldItem.apiId == newItem.apiId

        override fun areContentsTheSame(
            oldItem: MovieModel,
            newItem: MovieModel
        ): Boolean = oldItem == newItem
    }
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

    class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            result: MovieModel,
            onMovieItemClickListener: OnMovieItemClickListener? = null
        ) {
            binding.apply {
                movieImageView.fromUrl(TheMovieDBPosterBaseURL.plus(result.posterPath))
                movieTitleLabel.text = result.title
                root.setOnClickListener { onMovieItemClickListener?.onItemClick(result) }
            }
        }
    }

    fun interface OnMovieItemClickListener {
        fun onItemClick(trendingMovieModel: MovieModel)
    }
}