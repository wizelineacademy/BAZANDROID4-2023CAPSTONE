package com.jecruzv.capstonewl.ui.viewmodels

import com.jecruzv.local.model.MovieDetail


/**
 * The state data class for Movie Screen.
 */
data class MovieState(
    val isLoading: Boolean = false,
    val movie: MovieDetail? = null,
    val error: String = ""
)