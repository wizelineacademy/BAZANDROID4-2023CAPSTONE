package mx.jramon.subias.dbmovieproyect.movies.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import mx.jramon.subias.dbmovieproyect.utils.Constants
import mx.jramon.subias.dbmovieproyect.R
import mx.jramon.subias.dbmovieproyect.databinding.ItemMovieBinding
import mx.jramon.subias.dbmovieproyect.movies.domain.model.TvSerieEntity

class TvSerieListAdapter(
    private val listTvSerie:List<TvSerieEntity>,
    private val onSelect: (TvSerieEntity)->Unit
) : RecyclerView.Adapter<TvSerieListAdapter.ViewHolder>(){

    private lateinit var context: Context

    class ViewHolder ( itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vBind = ItemMovieBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
            R.layout.item_movie, parent, false))
    }

    override fun getItemCount() = listTvSerie.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vBind.apply {
            Glide.with(context)
                .load(Constants.IMAGE_API_PREFIX + listTvSerie[position].posterPath)
                .apply(RequestOptions.centerCropTransform())
                .into(ivItem)
            ivItem.clipToOutline = true
            ivItem.setOnClickListener { onSelect(listTvSerie[position]) }
        }
    }
}