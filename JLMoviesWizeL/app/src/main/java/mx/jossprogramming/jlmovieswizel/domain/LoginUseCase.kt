package mx.jossprogramming.jlmovieswizel.domain

import mx.jossprogramming.jlmovieswizel.data.models.AuthUserResult
import mx.jossprogramming.jlmovieswizel.data.providers.FirebaseAuthProvider
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: FirebaseAuthProvider
) {

    suspend operator fun invoke(user:String, password:String): AuthUserResult {
        return repository.createUserWithEmail(user,password)
    }
}