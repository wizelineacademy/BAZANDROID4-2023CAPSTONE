package com.andresrivas.bazpeliculasyseries.common.domain

import com.andresrivas.bazpeliculasyseries.tools.ResultAPI
import kotlinx.coroutines.flow.Flow

interface UseCase<Params, T> {
    fun execute(params: Params? = null): Flow<ResultAPI<T>>
}