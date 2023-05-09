package com.andresrivas.bazpeliculasyseries.domain.model

class MoviesVideoModel(
    var id: Int = 0,
    var resultList: List<MoviesVideoResultModel> = emptyList(),
)

data class MoviesVideoResultModel(
    var id: String = "",
    var key: String = "",
    var name: String = "",
    var site: String = "",
    var size: Int = 0,
    var type: String = "",
)
