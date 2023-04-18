package com.jecruzv.capstonewl.data

import com.jecruzv.capstonewl.data.model.Movie
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("movies") fun charactersAsync(): Deferred<Response<Movie>>
}