package com.jecruzv.capstonewl.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.erick.juarez.tmdb.util.CustomLinearLayoutManager
import com.jecruzv.capstonewl.R
import com.jecruzv.capstonewl.databinding.HomeFragmentBinding
import com.jecruzv.capstonewl.ui.adapters.MovieAdapter
import com.jecruzv.capstonewl.ui.adapters.PopularPagerAdapter
import com.jecruzv.capstonewl.ui.viewmodels.HomeViewModel
import com.jecruzv.capstonewl.util.DepthPageTransformer

class HomeFragment : Fragment(){

    lateinit var binding: HomeFragmentBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lateinit var adapter: MovieAdapter

        viewModel.getPopularMovies()
        viewModel.popular.observe(viewLifecycleOwner){ moviesResponse->
            binding.pagerPopularMovie.adapter = moviesResponse.results?.let { PopularPagerAdapter(it) }
            binding.pagerPopularMovie.setPageTransformer(true, DepthPageTransformer())
        }

        viewModel.getUpcomingMovies()
        viewModel.upcoming.observe(viewLifecycleOwner){ moviesResponse->
            binding.rvNowWatching.layoutManager = CustomLinearLayoutManager(requireContext())
            binding.rvNowWatching.adapter = MovieAdapter(moviesResponse){
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_detailFragment)
            }
        }

        viewModel.getTopRatedMovies()
        viewModel.topRated.observe(viewLifecycleOwner){moviesResponse->
            binding.rvTopRatedMovies.layoutManager = CustomLinearLayoutManager(requireContext())
            binding.rvTopRatedMovies.adapter=MovieAdapter(moviesResponse){
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_detailFragment)
            }
        }
    }
}