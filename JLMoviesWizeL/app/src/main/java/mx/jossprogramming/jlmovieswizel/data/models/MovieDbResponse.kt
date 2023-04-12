package mx.jossprogramming.jlmovieswizel.data.models

data class MovieDbResponse(
    var page: Int,
    var results: ArrayList<DetailMovies>,
    var total_pages: Int,
    var total_results: Int
)