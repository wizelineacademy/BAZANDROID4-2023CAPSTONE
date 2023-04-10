package mx.jramon.subias.dbmovieproyect.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import mx.jramon.subias.dbmovieproyect.movies.data.repositories.MovieRepositoryImpl
import mx.jramon.subias.dbmovieproyect.movies.domain.MovieTvRepository


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindMovieRepository (impl: MovieRepositoryImpl) : MovieTvRepository
}