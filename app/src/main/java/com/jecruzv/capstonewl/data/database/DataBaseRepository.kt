package com.jecruzv.capstonewl.data.database
import android.util.Log
import androidx.lifecycle.LiveData
import com.jecruzv.capstonewl.data.RetrofitClient
import com.jecruzv.capstonewl.data.database.dao.MoviesDao
import com.jecruzv.capstonewl.data.database.entities.PopularMoviesDB
import com.jecruzv.capstonewl.util.Constants.API_KEY
import javax.inject.Inject

class DataBaseRepository @Inject constructor(private val movieDao: MoviesDao) {
    val popularMovies: LiveData<List<PopularMoviesDB>> = movieDao.getPopularMovies()

    suspend fun insertPopularMovies() {
        try {
            val movies = RetrofitClient.clientApi.getPopularMovies(API_KEY).await().results!!.map {
                PopularMoviesDB(
                    it.id!!,
                    it.title!!,
                    it.overview,
                    it.release_date!!,
                    it.poster_path,
                    it.vote_average!!
                )
            }
            movieDao.insertPopularMovies(movies)
        } catch (e: Exception) {
            Log.e("ROOM", "Failed to insert popular movies: ${e.message}")
        }
    }
}