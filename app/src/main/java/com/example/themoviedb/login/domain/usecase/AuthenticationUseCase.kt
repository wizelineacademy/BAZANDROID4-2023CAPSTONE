package com.example.themoviedb.login.domain.usecase

import com.example.themoviedb.login.domain.LoginRepository
import com.example.themoviedb.util.ResultWrapper
import com.google.firebase.auth.FirebaseUser
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AuthenticationUseCase @Inject constructor(
    private val repository: LoginRepository
) {

    fun execute(email: String, password: String): Single<ResultWrapper<FirebaseUser>> {
        return repository.login(email, password)
    }
}
