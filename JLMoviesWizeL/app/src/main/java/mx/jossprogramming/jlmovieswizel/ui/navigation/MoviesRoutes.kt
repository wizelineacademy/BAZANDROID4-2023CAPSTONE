package mx.jossprogramming.jlmovieswizel.ui.navigation

sealed class MoviesRoutes(var route: String) {
    object LoginScreen : MoviesRoutes("route_login")
    object MoviesListScreen : MoviesRoutes("route_movies_list")
}