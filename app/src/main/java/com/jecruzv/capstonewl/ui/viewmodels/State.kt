package com.jecruzv.capstonewl.ui.viewmodels

data class State<T>(
    val isLoading: Boolean = false,
    val media: List<T> = emptyList(),
    val error: String = ""
)