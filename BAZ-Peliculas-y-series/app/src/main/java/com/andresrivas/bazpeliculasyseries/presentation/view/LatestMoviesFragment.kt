package com.andresrivas.bazpeliculasyseries.presentation.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.andresrivas.bazpeliculasyseries.data.mapper.transformToDetail
import com.andresrivas.bazpeliculasyseries.databinding.FragmentLatestMoviesBinding
import com.andresrivas.bazpeliculasyseries.presentation.view.adapters.LatestMoviesAdapter
import com.andresrivas.bazpeliculasyseries.presentation.viewmodel.MoviesViewModel
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LatestMoviesFragment : Fragment() {

    private lateinit var binding: FragmentLatestMoviesBinding
    private var moviesAdapter: LatestMoviesAdapter = LatestMoviesAdapter()
    private val playingNowViewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLatestMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configRecyclerView()
        playingNowViewModel.getLatestMovies()
        binding.swipeRefreshLayout.setOnRefreshListener { playingNowViewModel.getLatestMovies() }
        playingNowViewModel.latestMovies.observe(viewLifecycleOwner) {
            when (it) {
                is ResultAPI.OnSuccess -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    moviesAdapter.submitList(listOf(it.data))
                }
                is ResultAPI.OnFailure -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    Snackbar.make(binding.root, "Couldn't load movies" + it.exception.toString(), Toast.LENGTH_SHORT).show()
                }
                is ResultAPI.OnLoading -> {}
            }
        }

        moviesAdapter.setOnMovieItemClickListener { latestMoviesResultModel ->
            startActivity(Intent(requireContext(), MovieDetailActivity::class.java).apply {
                putExtra(
                    MovieDetailActivity.MOVIE_RESULT_MOVIE_INFO,
                    Gson().toJson(latestMoviesResultModel.transformToDetail())
                )
            })
        }
    }

    private fun configRecyclerView() {
        binding.playingNowRV.setHasFixedSize(true)
        binding.playingNowRV.layoutManager = LinearLayoutManager(context)
        binding.playingNowRV.adapter = moviesAdapter
    }
}