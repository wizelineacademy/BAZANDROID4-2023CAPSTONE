package com.example.themoviedb.domain.usecase

interface FlowBaseUseCase<out Result> {
    fun execute(): Result
}
