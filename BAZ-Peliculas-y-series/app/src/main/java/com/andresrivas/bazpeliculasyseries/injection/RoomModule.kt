package com.andresrivas.bazpeliculasyseries.injection

import android.content.Context
import androidx.room.Room
import com.andresrivas.bazpeliculasyseries.data.db.data.base.FavoriteMoviesDb
import com.andresrivas.bazpeliculasyseries.data.db.data.base.MovieDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun provideMoviesDataBase(@ApplicationContext context: Context): MovieDb {
        return Room.databaseBuilder(context, MovieDb::class.java, "movie").build()
    }

    @Singleton
    @Provides
    fun provideFavoritesDataBase(@ApplicationContext context: Context): FavoriteMoviesDb {
        return Room.databaseBuilder(context, FavoriteMoviesDb::class.java, "favoriteMovies").build()
    }
}
