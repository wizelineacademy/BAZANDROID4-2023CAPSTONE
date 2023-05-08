package com.jecruzv.capstonewl.ui.viewmodels

import com.jecruzv.local.model.Video


/**
 * The state data class for Movie Screen.
 */
data class MovieVideosState(
    val isLoading: Boolean = false,
    val videos: List<Video> = emptyList(),
    val error: String = ""
)