package com.jecruzv.capstonewl.ui.fragment

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.sqlite.db.SupportSQLiteQueryBuilder
import com.erick.juarez.tmdb.util.CustomLinearLayoutManager
import com.jecruzv.capstonewl.R
import com.jecruzv.capstonewl.data.model.MoviesDatabase
import com.jecruzv.capstonewl.databinding.HomeFragmentBinding
import com.jecruzv.capstonewl.ui.adapters.UpcomingMovieAdapter
import com.jecruzv.capstonewl.ui.adapters.PopularPagerAdapter
import com.jecruzv.capstonewl.ui.adapters.TopRatedMovieAdapter
import com.jecruzv.capstonewl.ui.viewmodels.HomeViewModel
import com.jecruzv.capstonewl.util.Constants
import com.jecruzv.capstonewl.util.DepthPageTransformer
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: HomeFragmentBinding
    private val viewModel: HomeViewModel by viewModels()

    private val connectivityManager by lazy {
        requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPopularMovies()
        lifecycleScope.launch {
            viewModel.popularMoviesState.collect { moviesResponse ->
                binding.pagerPopularMovie.adapter = moviesResponse?.let { PopularPagerAdapter(it.media) }
                binding.pagerPopularMovie.setPageTransformer(true, DepthPageTransformer())
            }
        }

        viewModel.getUpcomingMovies()
        viewModel.upcoming.observe(viewLifecycleOwner) { moviesResponse ->
                binding.rvUpcoming.layoutManager = CustomLinearLayoutManager(requireContext())
                binding.rvUpcoming.adapter = UpcomingMovieAdapter(moviesResponse) {
                    val bundle = bundleOf(Constants.PARAM_MOVIE_ID to it.id,
                        Constants.PARAM_MOVIE_RATING to it.vote_average)

                    Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_detailFragment,bundle)
                }
        }

        viewModel.getTopRatedMovies()
        lifecycleScope.launch {
            viewModel.topRated.collect { moviesResponse ->
                binding.rvTopRatedMovies.layoutManager = CustomLinearLayoutManager(requireContext())
                binding.rvTopRatedMovies.adapter = TopRatedMovieAdapter(moviesResponse.media) {
                    val bundle = bundleOf(Constants.PARAM_MOVIE_ID to it.id,
                        Constants.PARAM_MOVIE_RATING to it.vote_average)
                    Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_detailFragment,bundle) }

            }
        }

        observeConnectivity()
    }

    private fun observeConnectivity() {
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                //viewModel.getPopularMovies()

            }

            override fun onLost(network: Network) {
                // handle network loss
            }
        }

        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }
}