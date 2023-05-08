package com.jecruzv.capstonewl.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jecruzv.capstonewl.R
import com.jecruzv.capstonewl.databinding.ItemPopularViewPagerBinding
import com.jecruzv.capstonewl.databinding.ItemUpcomingMovieBinding
import com.jecruzv.capstonewl.domain.model.PopularMovie
import com.jecruzv.capstonewl.domain.model.UpcomingMovie
import com.jecruzv.capstonewl.util.Annotations

@Annotations("Entregable 1")
class UpcomingMovieAdapter(var popularMovieDetailList: List<UpcomingMovie>, val onItemClick: (UpcomingMovie) -> Unit) :
    RecyclerView.Adapter<UpcomingMovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(var view: ItemUpcomingMovieBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(model: UpcomingMovie, onItemClick: (UpcomingMovie) -> Unit) {
            itemView.setOnClickListener { onItemClick(model) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemUpcomingMovieBinding>(
                inflater,
                R.layout.item_upcoming_movie,
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

    fun update(popularMovieList: List<UpcomingMovie>) {
        this.popularMovieDetailList = popularMovieList
        notifyItemRangeChanged(0, popularMovieList.size)
    }
}