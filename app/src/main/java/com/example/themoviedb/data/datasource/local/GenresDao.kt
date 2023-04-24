package com.example.themoviedb.data.datasource.local

import androidx.room.Dao
import androidx.room.Query
import com.example.themoviedb.base.BaseDao

@Dao
interface GenresDao: BaseDao<GenreLocal> {

    @Query("SELECT * FROM GenresMovies WHERE idGenre IN (:ids)")
    fun getGenres(ids: List<Int>): List<GenreLocal>
}