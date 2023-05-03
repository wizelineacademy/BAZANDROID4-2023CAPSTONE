package com.example.tools

sealed interface ResultWrapper<out T> {
    class Success<T>(val data: T): ResultWrapper<T>
    class Error(val code: Int, val message: String): ResultWrapper<Nothing>
}