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
import com.andresrivas.bazpeliculasyseries.databinding.FragmentFavoriteMoviesBinding
import com.andresrivas.bazpeliculasyseries.presentation.view.adapters.MovieAdapter
import com.andresrivas.bazpeliculasyseries.presentation.viewmodel.FavoritesViewModel
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteMoviesFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteMoviesBinding
    private var moviesAdapter: MovieAdapter = MovieAdapter()
    private val favoritesViewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configRecyclerView()
        favoritesViewModel.getFavorites()
        binding.swipeRefreshLayout.setOnRefreshListener { favoritesViewModel.getFavorites() }
        favoritesViewModel.favorites.observe(viewLifecycleOwner) {
            when (it) {
                is ResultAPI.OnSuccess -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                    moviesAdapter.submitList(it.data)
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
        binding.favoriteMoviesRv.setHasFixedSize(true)
        binding.favoriteMoviesRv.layoutManager = LinearLayoutManager(context)
        binding.favoriteMoviesRv.adapter = moviesAdapter
    }
}