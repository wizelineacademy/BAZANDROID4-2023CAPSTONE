package com.edith.movies.features.movies.presentation.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.edith.movies.databinding.ActivityMainBinding
import com.edith.movies.core.data.database.model.MovieDb
import com.edith.movies.features.movies.presentation.view.adapter.MoviesAdapter
import com.edith.movies.features.movies.presentation.viewmodel.MoviesViewModel
import com.edith.movies.temp.SigInActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private  val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val moviesViewModel : MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupObservers()
        moviesViewModel.getMovies()
        moviesViewModel.getLastMovie()


        if(Firebase.auth.currentUser == null){
            startActivity(Intent(this, SigInActivity::class.java))
            finish()
        }

    }

    private fun setupObservers(){
        moviesViewModel.movies.observe(this){
            renderList(it)
        }


    }

    private fun renderList(list : List<MovieDb>){
        val moviesAdapter = MoviesAdapter()
        moviesAdapter.submitList(list)

        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            setHasFixedSize(true)
            adapter = moviesAdapter
        }
    }



}

