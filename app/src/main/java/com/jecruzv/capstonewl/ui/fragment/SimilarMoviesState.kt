package com.jecruzv.capstonewl.ui.fragment

import com.jecruzv.capstonewl.data.remote.dto.MovieDto
import com.jecruzv.capstonewl.domain.model.PopularMovie

/**
 * The state data class for Movie Screen.
 */
data class SimilarMoviesState(
    val isLoading: Boolean = false,
    val movies: List<PopularMovie> = emptyList(),
    val error: String = ""
)