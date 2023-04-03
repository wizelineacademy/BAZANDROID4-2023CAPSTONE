package com.example.themoviedb.domain.usecase

interface BaseUseCase<out Result> {
    suspend fun execute(): Result
}