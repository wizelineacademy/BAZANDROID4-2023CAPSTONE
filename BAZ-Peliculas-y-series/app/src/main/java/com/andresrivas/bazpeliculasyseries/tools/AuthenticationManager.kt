package com.andresrivas.bazpeliculasyseries.tools

import android.app.Activity
import android.content.Context
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import com.andresrivas.bazpeliculasyseries.R
import com.andresrivas.bazpeliculasyseries.tools.AuthenticationManager.Builder.Companion.GOOGLE_SIGN_IN
import com.andresrivas.bazpeliculasyseries.utilities.AccountModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class AuthenticationManager(builder: Builder) {

    private val registry: ActivityResultRegistry = builder.registry
    private val context: Context = builder.context

    private val successProcess: ((tokenModel: AccountModel) -> Unit)? = builder.successProcess
    private val failProcess: ((errorMessage: String) -> Unit)? = builder.failProcess
    private val signInProviderType: SignInProviderType = builder.signInProviderType

    private val googleSignInResult = registry.register(
        GOOGLE_SIGN_IN,
        ActivityResultContracts.StartActivityForResult(),
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            val account = task.getResult(ApiException::class.java)
            try {
                account?.let {
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                successProcess?.invoke(AccountModel(account.email.orEmpty()))
                            } else {
                                failProcess?.invoke("Sign in not success")
                            }
                        }
                }
            } catch (e: Exception) {
                failProcess?.invoke("")
            }
        } else {
            failProcess?.invoke("Operation cancelled")
        }
    }

    class Builder(
        var registry: ActivityResultRegistry,
        var context: Context,
    ) {

        var successProcess: ((tokenModel: AccountModel) -> Unit)? = null
        var failProcess: ((errorMessage: String) -> Unit)? = null
        var signInProviderType: SignInProviderType = SignInProviderType.GoogleSignIn

        fun addSuccessCallback(success: ((tokenModel: AccountModel) -> Unit)): Builder {
            this.successProcess = success
            return this
        }

        fun addFailCallback(fail: ((errorMessage: String) -> Unit)): Builder {
            this.failProcess = fail
            return this
        }

        fun addSignInProviderType(signInProviderType: SignInProviderType): Builder {
            this.signInProviderType = signInProviderType
            return this
        }

        fun build(): AuthenticationManager {
            return AuthenticationManager(this)
        }

        companion object {
            internal const val GOOGLE_SIGN_IN = "GOOGLE_SIGN_IN"
            internal const val EMAIL_SIGN_IN = "EMAIL_SIGN_IN"
        }
    }

    fun launch() {
        when (signInProviderType) {
            SignInProviderType.EmailSignIn -> {
                failProcess?.invoke("Email sign in not implemented yet :'(")
            }
            SignInProviderType.GoogleSignIn -> {
                val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(context.getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build()

                val googleClient = GoogleSignIn.getClient(context, googleConf)
                googleClient.signOut()
                googleSignInResult.launch(googleClient.signInIntent)
            }
        }
    }
}
