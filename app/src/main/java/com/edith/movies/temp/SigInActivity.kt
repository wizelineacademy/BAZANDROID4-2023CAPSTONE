package com.edith.movies.temp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.edith.movies.R
import com.edith.movies.databinding.ActivitySigInBinding
import com.edith.movies.features.movies.presentation.view.activity.MainActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SigInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySigInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySigInBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private val signInResult = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) {

    }

    public override fun onStart() {
        super.onStart()
        if (Firebase.auth.currentUser == null) {
            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setLogo(R.mipmap.ic_launcher)
                .setAvailableProviders(
                    listOf(
                        AuthUI.IdpConfig.EmailBuilder().build(),
                        AuthUI.IdpConfig.GoogleBuilder().build(),
                    )
                ).build()
            signInResult.launch(signInIntent)
        }else{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

}