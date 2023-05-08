package com.jecruzv.capstonewl.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jecruzv.capstonewl.R
import com.jecruzv.capstonewl.databinding.ItemTopratedMovieBinding
import com.jecruzv.capstonewl.util.Annotations
import com.jecruzv.local.model.TopRatedMovie

@Annotations("Entregable 1")
class TopRatedMovieAdapter(var popularMovieDetailList: List<TopRatedMovie>, val onItemClick: (TopRatedMovie) -> Unit) :
    RecyclerView.Adapter<TopRatedMovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(var view: ItemTopratedMovieBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(model: TopRatedMovie, onItemClick: (TopRatedMovie) -> Unit) {
            itemView.setOnClickListener { onItemClick(model) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemTopratedMovieBinding>(
                inflater,
                R.layout.item_toprated_movie,
                parent,
                false
            )
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return popularMovieDetailList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.view.popular = popularMovieDetailList[position]
        holder.bind(popularMovieDetailList[position], onItemClick)
    }

    fun update(popularMovieList: List<TopRatedMovie>) {
        this.popularMovieDetailList = popularMovieList
        notifyItemRangeChanged(0, popularMovieList.size)
    }
}