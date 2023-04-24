package mx.jossprogramming.jlmovieswizel.interceptors

import mx.jossprogramming.jlmovieswizel.common.Constantes
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Clase que intercepta las peticiones en retrofit para agregar el api_key y el language
 *
 * @author Jose Luis Pino Ucan
 */
class MovieInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val url: HttpUrl = request.url.newBuilder()
            .addQueryParameter("api_key", Constantes.API_KEY)
            .addQueryParameter("language", Constantes.LANGUAJE)
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}