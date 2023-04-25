package com.jecruzv.capstonewl.ui.fragment

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.jecruzv.capstonewl.R
import com.jecruzv.capstonewl.databinding.LoginFragmentBinding

class LoginFragment: Fragment() {

    lateinit var binding:LoginFragmentBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        googleSignInClient = GoogleSignIn.getClient(requireContext(), GoogleSignInOptions.DEFAULT_SIGN_IN)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            if(validFields())
                login()
        }

        binding.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.ietMail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tilMail.isErrorEnabled=false
                binding.tilMail.helperText=""
            }
        })

        binding.ietPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tilPassword.isErrorEnabled=false
                binding.tilPassword.helperText=""
            }
        })
    }

    private fun login() {
        binding.pbRegister.indeterminateDrawable.setColorFilter(Color.parseColor("#26bad3"), PorterDuff.Mode.MULTIPLY);
        binding.pbRegister.visibility= View.VISIBLE
        firebaseAuth.signInWithEmailAndPassword(binding.ietMail.text.toString(), binding.ietPassword.text.toString()).addOnCompleteListener {
            binding.pbRegister.visibility= View.GONE
            if (it.isSuccessful) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            } else
                Toast.makeText(context, "Log In failed ", Toast.LENGTH_SHORT).show()
        }
    }

    fun validFields(): Boolean {
        var isValid = true
        if (binding.ietMail.text.toString().isEmpty()) {
            binding.tilMail.isErrorEnabled=true
            binding.tilMail.helperText="El correo es obligatorio."
            binding.ietMail.requestFocus()
            isValid = false
        } else if(binding.ietPassword.text.toString().isEmpty()) {
            binding.tilPassword.isErrorEnabled=true
            binding.tilPassword.helperText="La contraseña es obligatoria."
            binding.ietPassword.requestFocus()
            isValid = false
        }
        return isValid
    }
}