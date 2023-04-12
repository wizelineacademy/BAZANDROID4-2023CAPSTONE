package mx.jossprogramming.jlmovieswizel.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.jossprogramming.jlmovieswizel.data.models.ServiceState
import mx.jossprogramming.jlmovieswizel.domain.LoginUseCase
import mx.jossprogramming.mymovieapp.ui.login.LoginUiState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
):ViewModel() {
    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState:StateFlow<LoginUiState> get() = _loginUiState

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading:LiveData<Boolean> = _isLoading

    private val _loginServiceState = MutableStateFlow<ServiceState>(ServiceState.ServiceDefaultState)
    val loginServiceState:StateFlow<ServiceState> = _loginServiceState

    fun onLoginChanged(email:String, password:String){
        _loginUiState.value = _loginUiState.value.copy(
            email = email,
            password = password,
            isLoginEnable = enableLogin(email, password)
        )
    }

    private fun enableLogin(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 5

    fun onLoginSelected(){
        viewModelScope.launch {
            _isLoading.value = true
            val result = loginUseCase(_loginUiState.value.email, _loginUiState.value.password)
            _loginServiceState.value = result.userCreated
            _isLoading.value = false
        }
    }

    fun resetLoginServiceState(){
        _loginServiceState.value = ServiceState.ServiceDefaultState
    }
}