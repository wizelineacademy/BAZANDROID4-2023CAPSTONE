package com.example.local

import com.example.local.dao.GenresDao
import com.example.local.dao.LatestDao
import com.example.local.dao.NowPlayingDao
import com.example.local.dao.TopRatedDao
import com.example.local.entity.GenreLocal
import com.example.local.entity.LatestLocal
import com.example.local.entity.NowPlayingLocal
import com.example.local.entity.NowPlayingLocalWithGenres
import com.example.local.entity.TopRatedLocal
import com.example.tools.ResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val nowPlayingDao: NowPlayingDao,
    private val latestDao: LatestDao,
    private val topRatedDao: TopRatedDao,
    private val genresDao: GenresDao
): MovieLocalDataSource {

    override fun getNowPlaying(): Flow<List<NowPlayingLocalWithGenres>> {
        return nowPlayingDao.observeAll()
    }

    override fun getLatest(): Flow<List<LatestLocal>> {
        return latestDao.observeAll()
    }

    override fun getTopRated(): Flow<List<TopRatedLocal>> {
        return topRatedDao.observeAll()
    }

    override suspend fun saveNowPlaying(movies: List<NowPlayingLocal>) {
        nowPlayingDao.insertAll(movies)
    }

    override suspend fun saveLatest(movies: List<LatestLocal>) {
        latestDao.insertAll(movies)
    }

    override suspend fun saveTopRated(movies: List<TopRatedLocal>) {
        topRatedDao.insertAll(movies)
    }

    override suspend fun saveGenres(genres: List<GenreLocal>): ResultWrapper<Boolean> {
        return try {
            genresDao.insertAll(genres)
            ResultWrapper.Success(true)
        } catch (e: Exception) {
            ResultWrapper.Error(0, e.message ?: "Algo salio mal")
        }
    }

    override suspend fun getGenresMovie(ids: List<Int>): ResultWrapper<List<GenreLocal>> {
        return try {
            ResultWrapper.Success(genresDao.getGenres(ids))
        } catch (e: Exception) {
            ResultWrapper.Error(0, e.message ?: "Algo salio mal")
        }
    }
}