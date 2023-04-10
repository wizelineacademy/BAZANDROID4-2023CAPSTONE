package com.example.themoviedb.login.domain

import com.example.themoviedb.util.ResultWrapper
import com.google.firebase.auth.FirebaseUser
import io.reactivex.rxjava3.core.Single

interface LoginRepository {
    fun login(email: String, password: String): Single<ResultWrapper<FirebaseUser>>
}