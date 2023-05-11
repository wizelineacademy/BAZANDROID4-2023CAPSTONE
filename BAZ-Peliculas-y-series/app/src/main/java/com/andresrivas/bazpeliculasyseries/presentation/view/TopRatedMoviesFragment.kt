package com.andresrivas.bazpeliculasyseries.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.andresrivas.bazpeliculasyseries.databinding.FragmentTopRatedBinding
import com.andresrivas.bazpeliculasyseries.presentation.view.adapters.MovieAdapter
import com.andresrivas.bazpeliculasyseries.presentation.viewmodel.MoviesViewModel
import com.andresrivas.common.utilities.ResultAPI
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopRatedMoviesFragment : Fragment() {

    private lateinit var binding: FragmentTopRatedBinding
    private var moviesAdapter: MovieAdapter = MovieAdapter()
    private val trendingViewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTopRatedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configRecyclerView()
        trendingViewModel.getTrendingMovies()
        binding.swipeRefreshLayout.setOnRefreshListener { trendingViewModel.getTrendingMovies() }
        trendingViewModel.movies.observe(viewLifecycleOwner) {
            when (it) {
                is ResultAPI.OnSuccess -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    moviesAdapter.submitList(it.data.resultList)
                }
                is ResultAPI.OnFailure -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    Snackbar.make(binding.root, "Couldn't load movies", Toast.LENGTH_SHORT).show()
                }
                is ResultAPI.OnLoading -> {}
            }
        }

        moviesAdapter.setOnMovieItemClickListener { trendingMoviesResultModel ->
            startActivity(
                Intent(requireContext(), MovieDetailActivity::class.java).apply {
                    putExtra(
                        MovieDetailActivity.MOVIE_RESULT_MOVIE_INFO,
                        Gson().toJson(trendingMoviesResultModel),
                    )
                },
            )
        }
    }

    private fun configRecyclerView() {
        binding.trendingRV.setHasFixedSize(true)
        binding.trendingRV.layoutManager = LinearLayoutManager(context)
        binding.trendingRV.adapter = moviesAdapter
    }
}
