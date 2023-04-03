package com.example.themoviedb.util

sealed interface ResultWrapper<out T> {
    class Success<T>(val data: T): ResultWrapper<T>
    class Error(val code: Int, message: String): ResultWrapper<Nothing>
}