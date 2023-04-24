package com.edith.movies.features.login



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.edith.movies.R
import com.edith.movies.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                        }
                    }.addOnFailureListener {
                        showAlert()
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
                        }
                    }.addOnFailureListener {
                           showAlert()
                    }
            }
        }



    }

    private fun showAlert(){

        val builder = AlertDialog.Builder(this.requireContext())
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error de autentificación")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String){

       findNavController().navigate(R.id.action_loginFragment_to_nowPlayingMoviesFragment)

    }




}