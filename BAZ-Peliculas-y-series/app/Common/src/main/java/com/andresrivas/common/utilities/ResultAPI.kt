package com.andresrivas.common.utilities

sealed class ResultAPI<out T : Any?> {
    class OnSuccess<out T : Any?>(val data: T) : ResultAPI<T>()
    class OnFailure(val exception: Throwable) : ResultAPI<Nothing>()
    class OnLoading<out T : Any?> : ResultAPI<T>()
}
