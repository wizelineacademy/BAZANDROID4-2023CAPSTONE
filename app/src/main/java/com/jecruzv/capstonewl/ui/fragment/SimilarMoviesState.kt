package com.jecruzv.capstonewl.ui.fragment

import com.jecruzv.local.model.PopularMovie

/**
 * The state data class for Movie Screen.
 */
data class SimilarMoviesState(
    val isLoading: Boolean = false,
    val movies: List<PopularMovie> = emptyList(),
    val error: String = ""
)