package mx.jossprogramming.remote.models

data class MovieDbResponse(
    var page: Int,
    var results: ArrayList<DetailMovies>,
    var total_pages: Int,
    var total_results: Int
)