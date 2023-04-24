package mx.jramon.subias.dbmovieproyect.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseRepository<T:Any> {

    protected open lateinit var api:T


    suspend fun<T> onResponse(
        callFunction: suspend () -> T,
        onComplete:(success:Boolean, data: T?)->Unit
    ){
        when(val response = safeApiCall(Dispatchers.IO){
            callFunction.invoke()
        }){
            is ResponseApi.Success ->{
                onComplete(true, response.value)
            }

            is ResponseApi.GenericError ->{
                onComplete(false, null)
            }
        }
    }

     suspend inline fun <T> safeApiCall(
        dispatcher:CoroutineDispatcher,
        crossinline apiCall:suspend () -> T
    ) : ResponseApi<T> {
        return withContext(dispatcher){
            try {
                ResponseApi.Success(apiCall.invoke())
            }catch (throwable: Throwable){
                ResponseApi.GenericError(code = null, "ERROR")
            }
        }
    }

}

sealed class ResponseApi<out T>{
    data class Success<out T > (val value:T): ResponseApi<T>()

    data class GenericError(
        val code:Int? = null,
        val error: String? = null
    ): ResponseApi<Nothing>()
}