package mx.jossprogramming.jlmovieswizel.domain

import javax.inject.Inject
import mx.jossprogramming.jlmovieswizel.data.models.AuthUserResult
import mx.jossprogramming.jlmovieswizel.data.providers.FirebaseAuthProvider

class LoginUseCase @Inject constructor(
    private val repository: FirebaseAuthProvider
) {

    suspend operator fun invoke(user: String, password: String): AuthUserResult {
        return repository.createUserWithEmail(user, password)
    }
}