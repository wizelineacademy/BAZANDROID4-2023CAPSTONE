package mx.jramon.subias.dbmovieproyect.movies.domain.model

data class RequestToken(
    val username:String,
    val pass:String,
    val requestTkn:String
)
