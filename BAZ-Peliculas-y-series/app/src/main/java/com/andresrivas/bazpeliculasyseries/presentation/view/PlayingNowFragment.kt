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
import com.andresrivas.bazpeliculasyseries.databinding.FragmentPlayingNowBinding
import com.andresrivas.bazpeliculasyseries.presentation.view.adapters.MovieAdapter
import com.andresrivas.bazpeliculasyseries.presentation.viewmodel.PlayingNowViewModel
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayingNowFragment : Fragment() {

    private lateinit var binding: FragmentPlayingNowBinding
    private var moviesAdapter: MovieAdapter = MovieAdapter()
    private val playingNowViewModel: PlayingNowViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayingNowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configRecyclerView()
        playingNowViewModel.getPlayingNowMovies()
        binding.swipeRefreshLayout.setOnRefreshListener { playingNowViewModel.getPlayingNowMovies() }
        playingNowViewModel.movies.observe(viewLifecycleOwner) {
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
            startActivity(Intent(requireContext(), MovieDetailActivity::class.java).apply {
                putExtra(
                    MovieDetailActivity.MOVIE_RESULT_MOVIE_INFO,
                    Gson().toJson(trendingMoviesResultModel)
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