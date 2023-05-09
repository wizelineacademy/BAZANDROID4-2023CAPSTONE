package com.andresrivas.bazpeliculasyseries.data.repository

import com.andresrivas.bazpeliculasyseries.data.repository.datasource.FavoritesDataSource

class FavoritesRepository(
    private val localDataSource: FavoritesDataSource,
) {
    fun getFavorites() {}
}
