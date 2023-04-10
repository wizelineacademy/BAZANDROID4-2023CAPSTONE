package com.example.themoviedb.login.data.repository

import com.example.themoviedb.login.domain.LoginRepository
import com.example.themoviedb.util.ResultWrapper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
): LoginRepository {

    override fun login(email: String, password: String): Single<ResultWrapper<FirebaseUser>> {
        return Single.create { emitter ->
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = task.result.user
                        if(user != null) {
                            emitter.onSuccess(ResultWrapper.Success(user))
                        } else {
                            emitter.onSuccess(ResultWrapper.Error(0, "Algo salio mal"))
                        }
                    } else {
                        emitter.onSuccess(
                            ResultWrapper.Error(
                                0,
                                task.exception?.message ?: "Algo salio mal"
                            )
                        )
                    }
                }
        }
    }
}