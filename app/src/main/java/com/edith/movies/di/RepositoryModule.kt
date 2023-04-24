package com.edith.movies.di

import com.edith.movies.core.data.database.dao.GenderDao
import com.edith.movies.core.data.database.dao.MoviesDao
import com.edith.movies.core.service.api.ApiService
import com.edith.movies.features.movies.domain.MoviesDbRepositoryImp
import com.edith.movies.features.movies.domain.MoviesDbRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [DataBaseModule::class])
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(apiService: ApiService, moviesDao: MoviesDao, genderDao: GenderDao): MoviesDbRepository {
        return MoviesDbRepositoryImp(apiService,moviesDao, genderDao)
    }

}