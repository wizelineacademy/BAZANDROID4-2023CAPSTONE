package com.example.themoviedb.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.themoviedb.presentation.compose.MovieDetail
import com.example.themoviedb.presentation.compose.ui.theme.BAZANDROID42023CAPSTONETheme
import com.example.themoviedb.presentation.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private val movieViewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bundle = arguments
        val movie = bundle?.getParcelable("movie") ?: com.example.core.model.MovieModel()

        movieViewModel.getGenresMovie(movie.genreIds)

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner))
            setContent {
                BAZANDROID42023CAPSTONETheme {
                    MovieDetail(movie = movie, movieViewModel) {
                        requireActivity().onBackPressed()
                    }
                }
            }
        }
    }
}
