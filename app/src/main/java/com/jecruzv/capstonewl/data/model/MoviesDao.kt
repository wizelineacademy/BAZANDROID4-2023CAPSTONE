package com.jecruzv.capstonewl.data.model

import androidx.room.*
import com.jecruzv.capstonewl.domain.model.*
import com.jecruzv.capstonewl.util.Annotations
import kotlinx.coroutines.flow.Flow

@Annotations("Entregable 2")
@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPopularMovies(popularMovies: List<PopularMovie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUpcomingMovies(upcomingMovie: List<UpcomingMovie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTopRatedMovies(topRatedMovie: List<TopRatedMovie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movieDetail: MovieDetail)

    @Transaction
    @Query("SELECT * FROM popular_movies ORDER BY popularity DESC")
    fun getPopularMovies(): Flow<List<PopularMovie>>

    @Transaction
    @Query("SELECT * FROM upcoming_movies ORDER BY popularity DESC")
    fun getUpcomingMovies(): Flow<List<UpcomingMovie>>

    @Transaction
    @Query("SELECT * FROM toprated_movies ORDER BY popularity DESC")
    fun getTopRatedMovies(): Flow<List<TopRatedMovie>>

    @Transaction
    @Query("SELECT * FROM movies WHERE id = :movieId")
    suspend fun getMovie(movieId: Int): MovieDetail?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: List<Genres>)

    @Transaction
    @Query(
        """
        SELECT *
FROM movies
WHERE id IN (
  SELECT movieId
  FROM movies_genres
  WHERE genreId IN (
    SELECT id
    FROM genres
    WHERE name = :genreName
  )
)
    """
    )
    fun getMoviesByGenre(genreName: String): Flow<List<MovieDetail>>

}