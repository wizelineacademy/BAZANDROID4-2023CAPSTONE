package com.edith.movies.features.movies.presentation.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.edith.movies.databinding.ActivitysLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivitys : AppCompatActivity() {

    private val binding: ActivitysLoginBinding by lazy { ActivitysLoginBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        sigInFirebase()
    }

    private fun sigInFirebase(){
       binding.btReg.setOnClickListener {
            if (binding.etEmail.text.isNotEmpty() && binding.etPassword.text.isNotEmpty()) {

                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()).addOnCompleteListener {
                        if (it.isSuccessful){
                                showHome(it.result.user?.email ?: "")
                        }else{
                            showAlert()
                        }
                    }
            }
        }

        binding.btLogin.setOnClickListener {
            if (binding.etEmail.text.isNotEmpty() && binding.etPassword.text.isNotEmpty()) {

                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(binding.etEmail.text.toString(),
                        binding.etPassword.text.toString()).addOnCompleteListener {
                        if (it.isSuccessful){
                            showHome(it.result.user?.email ?: "")
                        }else{
                            showAlert()
                        }
                    }
            }
        }



    }

    private fun showAlert(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error de autentificación")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String){

        val homeIntent=  Intent(this, MainActivity::class.java ).apply {
            putExtra("email", email)
        }
        startActivity(homeIntent)
    }
}