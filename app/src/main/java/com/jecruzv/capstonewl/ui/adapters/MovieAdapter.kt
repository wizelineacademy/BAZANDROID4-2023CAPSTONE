package com.jecruzv.capstonewl.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jecruzv.capstonewl.R
import com.jecruzv.capstonewl.data.model.Movie
import com.jecruzv.capstonewl.databinding.ItemMovieBinding
import com.jecruzv.capstonewl.databinding.ItemPopularViewPagerBinding
import com.jecruzv.capstonewl.util.Annotations

@Annotations("Entregable 1")
class MovieAdapter(var movieDetailList: List<Movie>, val onItemClick: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(var view: ItemPopularViewPagerBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(model: Movie, onItemClick: (Movie) -> Unit) {
            itemView.setOnClickListener { onItemClick(model) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemPopularViewPagerBinding>(
                inflater,
                R.layout.item_popular_view_pager,
                parent,
                false
            )
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieDetailList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.view.popular = movieDetailList[position]
        holder.bind(movieDetailList[position], onItemClick)
    }

    fun update(movieList: List<Movie>) {
        this.movieDetailList = movieList
        notifyItemRangeChanged(0, movieList.size)
    }
}