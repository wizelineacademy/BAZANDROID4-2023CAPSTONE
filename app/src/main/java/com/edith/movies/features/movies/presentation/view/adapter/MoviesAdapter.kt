package com.edith.movies.features.movies.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edith.movies.core.data.MovieDb
import com.edith.movies.databinding.ViewMovieItemBinding


class MoviesAdapter : ListAdapter<MovieDb, MoviesAdapter.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ViewMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(getItem(position)){
          val im = "https://image.tmdb.org/t/p/w500/"
                binding.tvTitle.text = this.title
                Glide.with(binding.ivMoview)
                    .load(im + this.backdrop_path)
                    .into(binding.ivMoview)

            }
        }
    }

    inner class ViewHolder(val binding : ViewMovieItemBinding) : RecyclerView.ViewHolder(binding.root)

     class DiffUtilCallback(): DiffUtil.ItemCallback<MovieDb>(){
        override fun areItemsTheSame(oldItem: MovieDb, newItem: MovieDb): Boolean =
            oldItem==newItem

        override fun areContentsTheSame(oldItem: MovieDb, newItem: MovieDb): Boolean =
            oldItem.id==newItem.id

    }



}