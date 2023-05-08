package mx.jossprogramming.jlmovieswizel.data.models

import com.google.firebase.auth.FirebaseUser

data class AuthUserResult(
    var userCreated: ServiceState = ServiceState.ServiceDefaultState,
    var message: String = "",
    var currentUser: FirebaseUser? = null
)