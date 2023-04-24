package mx.jossprogramming.jlmovieswizel.common.utils.fakes

import mx.jossprogramming.remote.models.DetailMovies
import mx.jossprogramming.remote.models.MovieDbResponse

object MoviesFakes {
    val listNowPlayingFake = arrayListOf<DetailMovies>(
        DetailMovies(
            id = 76600,
            title = "Avatar: The Way of Water",
            poster_path = "https://image.tmdb.org/t/p/original/5ScPNT6fHtfYJeWBajZciPV3hEL.jpg",
            genre_ids = emptyList(),
            overview = "Set more than a decade after the events of the first film, learn the story of the Sully family (Jake, Neytiri, and their kids), the trouble that follows them, the lengths they go to keep each other safe, the battles they fight to stay alive, and the tragedies they endure.",
            vote_average = 81.0,
        ),
        DetailMovies(
            id = 436270,
            title = "Black Adam",
            poster_path = "https://image.tmdb.org/t/p/original/pFlaoHTZeyNkG83vxsAJiGzfSsa.jpg",
            genre_ids = emptyList(),
            overview = "Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods—and imprisoned just as quickly—Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.",
            vote_average = 72.0,
        )
    )

    val MovieResponseFake = MovieDbResponse(
        page = 1,
        results = listNowPlayingFake,
        total_pages = 1,
        total_results = 1
    )
}