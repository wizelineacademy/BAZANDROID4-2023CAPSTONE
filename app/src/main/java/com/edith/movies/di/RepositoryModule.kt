package com.edith.movies.di

import com.edith.movies.core.service.api.ApiService
import com.edith.movies.features.movies.data.MoviesDbRepositoryImp
import com.edith.movies.features.movies.domain.MoviesDbRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(apiService: ApiService): MoviesDbRepository {
        return MoviesDbRepositoryImp(apiService)
    }

}