package mx.jramon.subias.dbmovieproyect.login.domain

interface LoginRepository {

    suspend fun signInWithEmailAndPassword()
}