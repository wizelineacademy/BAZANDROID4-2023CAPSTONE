package mx.jossprogramming.jlmovieswizel.data.providers

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import kotlinx.coroutines.tasks.await
import mx.jossprogramming.jlmovieswizel.common.Constantes
import mx.jossprogramming.jlmovieswizel.data.models.AuthUserResult
import mx.jossprogramming.jlmovieswizel.data.models.ServiceState

/**
 *  Esta clase es el provider que hace login en firebase y resgistra a los usuarios
 *
 *  @author Jose Luis Pino Ucan
 */
class FirebaseAuthProvider @Inject constructor() {
    private var auth: FirebaseAuth = Firebase.auth

    suspend fun signInWithEmail(email: String, password: String): AuthUserResult {
        val mAuthUserResult = AuthUserResult()
        val task = auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("mensaje", "Login success")
                    mAuthUserResult.currentUser = auth.currentUser
                    mAuthUserResult.userCreated = ServiceState.ServiceSuccessState
                    mAuthUserResult.message = "Login success"
                } else {
                    Log.d("mensaje", "Login fail")
                    mAuthUserResult.userCreated = ServiceState.ServiceErrorState
                    mAuthUserResult.message = "Login Fail"
                }
            }.addOnFailureListener {
                // If sign in fails, display a message to the user.
                Log.w("mensaje", "Login :failure", it)
                mAuthUserResult.userCreated = ServiceState.ServiceErrorState
                mAuthUserResult.message = it.message ?: ""
            }

        try {
            task.await()
        } catch (e: Exception) {
            mAuthUserResult.userCreated = ServiceState.ServiceErrorState
            mAuthUserResult.message = "Login Fail ${e.message}"
        }
        return mAuthUserResult
    }
    suspend fun createUserWithEmail(email: String, password: String): AuthUserResult {
        var mAuthUserResult = AuthUserResult()
        val task = auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("mensaje", "createUserWithEmail:success")
                    mAuthUserResult.userCreated = ServiceState.ServiceSuccessState
                    mAuthUserResult.currentUser = auth.currentUser
                    mAuthUserResult.message = "User created success"
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("mensaje", "createUserWithEmail:failure", task.exception)
                    mAuthUserResult.userCreated = ServiceState.ServiceErrorState
                    mAuthUserResult.message = task.exception?.message ?: ""
                }
            }
            .addOnFailureListener {
                Log.w("mensaje", "createUserWithEmail:addOnFailureListener ${it.message}")
                mAuthUserResult.userCreated = ServiceState.ServiceErrorState
                mAuthUserResult.message = it.message ?: ""
            }
        try {
            task.await()
        } catch (e: Exception) {
            Log.e("mensaje", "createUserWithEmail:addOnFailureListener ${e.message}")
            mAuthUserResult.userCreated = ServiceState.ServiceErrorState
            mAuthUserResult.message = e.message ?: ""
            if (e.message!!.contains(Constantes.ERROR_USER_IS_ALREADY_CREATED)) {
                mAuthUserResult = signInWithEmail(email, password)
            }
        }
        return mAuthUserResult
    }
}