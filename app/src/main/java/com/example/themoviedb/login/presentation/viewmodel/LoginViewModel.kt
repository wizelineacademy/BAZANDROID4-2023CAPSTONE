package com.example.themoviedb.login.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedb.login.domain.usecase.AuthenticationUseCase
import com.example.themoviedb.util.ResultWrapper
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase
) : ViewModel() {

    private val _loginState = MutableLiveData<ResultWrapper<FirebaseUser?>>()
    val loginState: LiveData<ResultWrapper<FirebaseUser?>> get() = _loginState

    fun doLogin(email: String, password: String) {
        viewModelScope.launch {
            authenticationUseCase.execute(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(
                    {
                        _loginState.postValue(it)
                    },
                    {}
                )
        }
    }
}
