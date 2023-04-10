package com.example.themoviedb.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviedb.databinding.FragmentLatestBinding
import com.example.themoviedb.presentation.adapter.MovieAdapter
import com.example.themoviedb.presentation.viewmodel.MovieViewModel
import com.example.themoviedb.util.ResultWrapper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class LatestFragment : Fragment() {

    private lateinit var binding: FragmentLatestBinding
    private val movieViewModel: MovieViewModel by viewModels()
    private val movieAdapter = MovieAdapter(MovieAdapter.MovieDiffCallback)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (::binding.isInitialized.not()) {
            binding = FragmentLatestBinding.inflate(inflater, container, false)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieViewModel.callLatestMovie()

        binding.rvLatest.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        lifecycleScope.launch(Dispatchers.IO) {
            movieViewModel.latestState.collect {
                it?.let {
                    when(it) {
                        is ResultWrapper.Error -> {}
                        is ResultWrapper.Success -> {
                            withContext(Dispatchers.Main) {
                                movieAdapter.submitList(mutableListOf(it.data))
                            }
                        }
                    }
                }
            }
        }
    }
}