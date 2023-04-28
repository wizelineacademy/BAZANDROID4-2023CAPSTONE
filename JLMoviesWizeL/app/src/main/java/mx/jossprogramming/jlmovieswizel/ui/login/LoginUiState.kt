package mx.jossprogramming.jlmovieswizel.ui.login

data class LoginUiState(
    var email: String = "",
    var password: String = "",
    var isLoginEnable: Boolean = false
)