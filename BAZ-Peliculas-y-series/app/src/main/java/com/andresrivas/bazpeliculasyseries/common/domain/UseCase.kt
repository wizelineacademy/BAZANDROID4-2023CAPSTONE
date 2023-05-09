package com.andresrivas.bazpeliculasyseries.common.domain

import com.andresrivas.bazpeliculasyseries.tools.ResultAPI
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface UseCase<Params, T> {
    fun execute(params: Params? = null): Flow<ResultAPI<T>>
}

interface UseCaseRxJava<Params, T> {
    fun execute(params: Params? = null): Single<ResultAPI<T>>
}
