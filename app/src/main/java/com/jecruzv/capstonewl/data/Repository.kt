package com.jecruzv.capstonewl.data

import android.util.Log
import com.jecruzv.capstonewl.data.model.Movie
import com.jecruzv.capstonewl.data.model.MoviesResponse
import com.jecruzv.capstonewl.util.Annotations
import com.jecruzv.capstonewl.util.Constants.API_KEY
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

@Annotations("Entregable 1")
class Repository() {

    suspend fun getPopularMovies(): Deferred<MoviesResponse> {
        return withContext(Dispatchers.Main) {
            RetrofitClient.clientApi.getPopularMovies(API_KEY)
        }
    }

    suspend fun getTopRatedMovies(): Deferred<MoviesResponse> {
        return withContext(Dispatchers.Main) {
            RetrofitClient.clientApi.getTopRatedMovies(API_KEY)
        }
    }

    @Annotations("Entregable 2")
    suspend fun getTopRatedMoviesFlow(): Flow<Deferred<MoviesResponse>> {
        return flow {
            emit(RetrofitClient.clientApi.getTopRatedMovies(API_KEY))
        }.catch { e ->
            Log.e("error",e.message.toString())
        }
    }
    fun getUpcomingMovies(): Single<List<Movie>> {
        return RetrofitClient.clientApiRx.getUpcomingMovies(API_KEY)
            .map { response -> response.results }
    }
}