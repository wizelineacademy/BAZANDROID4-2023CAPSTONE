package com.andresrivas.bazpeliculasyseries.data.db.dao

import androidx.room.*
import com.andresrivas.bazpeliculasyseries.domain.model.MovieModel

@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieModel")
    fun getAll(): List<MovieModel>

    @Query("SELECT * FROM MovieModel WHERE id = :id")
    fun getById(id: Int): MovieModel

    @Update
    fun update(movieModel: List<MovieModel>)

    @Insert
    fun insert(movieModel: List<MovieModel>): List<Long>

    @Upsert
    fun insertIfNoExist(movieModel: List<MovieModel>): List<Long>

    @Delete
    fun delete(movieModel: List<MovieModel>)
}