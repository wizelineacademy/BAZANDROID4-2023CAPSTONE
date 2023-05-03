package com.example.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.local.entity.GenreLocal
import com.example.tools.BaseDao

@Dao
interface GenresDao: BaseDao<GenreLocal> {

    @Query("SELECT * FROM GenresMovies WHERE idGenre IN (:ids)")
    fun getGenres(ids: List<Int>): List<GenreLocal>
}