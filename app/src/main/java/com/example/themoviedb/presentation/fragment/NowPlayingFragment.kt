package com.example.themoviedb.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themoviedb.databinding.FragmentNowPlayingBinding
import com.example.themoviedb.presentation.adapter.MovieAdapter
import com.example.themoviedb.presentation.adapter.MovieAdapter.Companion.MovieDiffCallback
import com.example.themoviedb.presentation.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class NowPlayingFragment : Fragment() {

    private val movieViewModel: MovieViewModel by viewModels()
    private val movieAdapter = MovieAdapter(MovieDiffCallback)
    private lateinit var binding: FragmentNowPlayingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieViewModel.callNowPlayingMovies()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (::binding.isInitialized.not()) {
            binding = FragmentNowPlayingBinding.inflate(inflater, container, false)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMovies.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }

        lifecycleScope.launch(Dispatchers.IO) {
            movieViewModel.nowPlayingFlow.collect{
                withContext(Dispatchers.Main) {
                    movieAdapter.submitList(it)
                }
            }
        }
    }
}