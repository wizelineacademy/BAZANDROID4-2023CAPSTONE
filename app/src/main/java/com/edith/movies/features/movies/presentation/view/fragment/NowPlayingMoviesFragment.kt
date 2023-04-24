package com.edith.movies.features.movies.presentation.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.edith.movies.core.data.database.model.Movie
import com.edith.movies.databinding.FragmentNowPlayingMoviesBinding
import com.edith.movies.features.movies.presentation.view.adapter.NowPlayingAdapter
import com.edith.movies.features.movies.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NowPlayingMoviesFragment : Fragment() {
    private var _binding: FragmentNowPlayingMoviesBinding? = null
    private val binding get() = _binding!!
    private val moviesViewModel : MoviesViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNowPlayingMoviesBinding.inflate(inflater, container, false)
        return binding.root }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesViewModel.getNowPlayingMovies()
        setupObservers()

    }

    private fun setupObservers(){
        moviesViewModel.moviesModel.observe(viewLifecycleOwner){
            renderList(it)
        }
    }


    private fun renderList(list : List<Movie>){
        val nowPlayingAdapter = NowPlayingAdapter()
        nowPlayingAdapter.submitList(list)

        binding.rvMoviesNow.apply {
            context?.let{ctx ->
                layoutManager = GridLayoutManager(ctx, 3)
                setHasFixedSize(true)
                adapter = nowPlayingAdapter
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}