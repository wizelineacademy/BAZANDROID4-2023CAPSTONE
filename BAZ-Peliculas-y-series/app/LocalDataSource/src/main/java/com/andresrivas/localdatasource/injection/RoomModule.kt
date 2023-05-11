package com.andresrivas.localdatasource.injection

import android.content.Context
import androidx.room.Room
import com.andresrivas.localdatasource.db.data.base.FavoriteMoviesDb
import com.andresrivas.localdatasource.db.data.base.NowPlayingMoviesDb
import com.andresrivas.localdatasource.db.data.base.TopRatedMoviesDb
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
    fun provideNoWPlayingMoviesDataBase(@ApplicationContext context: Context): NowPlayingMoviesDb {
        return Room.databaseBuilder(context, NowPlayingMoviesDb::class.java, "nowPlayingMovies")
            .build()
    }

    @Singleton
    @Provides
    fun provideFavoritesDataBase(@ApplicationContext context: Context): FavoriteMoviesDb {
        return Room.databaseBuilder(context, FavoriteMoviesDb::class.java, "favoriteMovies").build()
    }

    @Singleton
    @Provides
    fun provideTopRatedMoviesDataBase(@ApplicationContext context: Context): TopRatedMoviesDb {
        return Room.databaseBuilder(context, TopRatedMoviesDb::class.java, "topRatedMovies").build()
    }
}
