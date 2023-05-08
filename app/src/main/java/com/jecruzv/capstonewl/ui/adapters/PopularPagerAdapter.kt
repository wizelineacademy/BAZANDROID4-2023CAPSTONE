package com.jecruzv.capstonewl.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.viewpager.widget.PagerAdapter
import com.jecruzv.capstonewl.R
import com.jecruzv.capstonewl.databinding.ItemPopularViewPagerBinding
import com.jecruzv.capstonewl.util.Annotations
import com.jecruzv.local.Constants
import com.jecruzv.local.model.PopularMovie

@Annotations("Entregable 1")
class PopularPagerAdapter(var popularPopularMovieList: List<PopularMovie>) :
    PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val binding = DataBindingUtil.inflate<ItemPopularViewPagerBinding>(
            inflater,
            R.layout.item_popular_view_pager,
            container,
            false
        )
        binding.popular = popularPopularMovieList[position]
        binding.root.setOnClickListener {
            val bundle = bundleOf(
                Constants.PARAM_MOVIE_ID to popularPopularMovieList[position].id,
                Constants.PARAM_MOVIE_RATING to popularPopularMovieList[position].vote_average)
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_detailFragment,bundle)
        }
        container.addView(binding.root)

        return binding.root
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = popularPopularMovieList.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}