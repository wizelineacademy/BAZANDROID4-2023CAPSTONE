package com.edith.movies.features.movies.presentation.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.edith.movies.core.data.database.model.MovieDb
import com.edith.movies.databinding.FragmentLastMoviesBinding
import com.edith.movies.features.movies.presentation.view.adapter.MoviesAdapter
import com.edith.movies.features.movies.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LastMoviesFragment : Fragment() {
    private var _binding: FragmentLastMoviesBinding? = null
    private val binding get() = _binding!!
    private val moviesViewModel : MoviesViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLastMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()

        moviesViewModel.getMovies()

        /*if(Firebase.auth.currentUser == null){
            findNavController().navigate(R.id.action_loginFragment_to_secondFragment)

        }*/

    }

    private fun setupObservers(){
        moviesViewModel.movies.observe(viewLifecycleOwner){
            renderList(it)
        }


    }

    private fun renderList(list : List<MovieDb>){
        val moviesAdapter = MoviesAdapter()
        moviesAdapter.submitList(list)

        binding.rvMoviesLast.apply {
            context?.let{ctx ->
                layoutManager = GridLayoutManager(ctx, 3)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LastMoviesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LastMoviesFragment().apply {
                arguments = Bundle().apply {
                   /* putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)*/
                }
            }
    }
}