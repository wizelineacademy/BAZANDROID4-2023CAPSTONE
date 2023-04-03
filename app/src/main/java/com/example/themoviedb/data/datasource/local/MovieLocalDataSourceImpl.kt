package com.example.themoviedb.data.datasource.local

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val nowPlayingDao: NowPlayingDao,
    private val latestDao: LatestDao,
    private val topRatedDao: TopRatedDao
): MovieLocalDataSource {

    override fun getNowPlaying(): Flow<List<NowPlayingLocal>> {
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
}