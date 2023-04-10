package com.example.themoviedb.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.themoviedb.MainActivity
import com.example.themoviedb.databinding.ActivityLoginBinding
import com.example.themoviedb.login.presentation.viewmodel.LoginViewModel
import com.example.themoviedb.util.ResultWrapper
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    private val signInResult = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) {
        it?.let {
           if (it.resultCode == RESULT_OK ) {
               startActivity(Intent(this, MainActivity::class.java))
               finish()
           }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (::binding.isInitialized.not())
            binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseAuth.getInstance().currentUser?.let {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .build()

        binding.tvSignUp.setOnClickListener {
            signInResult.launch(signInIntent)
        }

        binding.loginButton.setOnClickListener {
            if (binding.emailEditText.text.toString()
                    .isNotEmpty() && binding.passwordEditText.text.toString().isNotEmpty()
            ) {
                loginViewModel.doLogin(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString(),
                )
            }
        }

        loginViewModel.loginState.observe(this) {
            when(it) {
                is ResultWrapper.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                is ResultWrapper.Success -> {
                    it.data?.let {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                }
            }
        }
    }
}