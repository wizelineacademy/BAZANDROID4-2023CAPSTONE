package com.example.themoviedb.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.model.MovieModel
import com.example.themoviedb.R
import com.example.themoviedb.databinding.ItemMovieBinding

class MovieAdapter(
    private val itemCallback: DiffUtil.ItemCallback<MovieModel>
): ListAdapter<MovieModel, MovieAdapter.MovieVH>(itemCallback) {

    private var itemClickListener: ItemClickListener? = null

    fun interface ItemClickListener {
        fun onItemClick(movie: MovieModel)
    }

    fun setItemClickListener(listener: ItemClickListener) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        val binding = DataBindingUtil.inflate<ItemMovieBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )
        return MovieVH(binding)
    }

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieVH(itemView: ViewDataBinding) : RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView as ItemMovieBinding

        init {
            binding.cardMovie.setOnClickListener {
                itemClickListener?.onItemClick(getItem(adapterPosition))
            }
        }

        fun bind(item: MovieModel) {
            binding.movie = item
        }
    }

    companion object {
        val MovieDiffCallback = object: DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}