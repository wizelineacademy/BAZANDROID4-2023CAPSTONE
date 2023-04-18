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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.jecruzv.capstonewl.R
import com.jecruzv.capstonewl.databinding.RegisterFragmentBinding

class RegisterFragment : Fragment(){

    lateinit var binding:RegisterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            if(validFields())
                registrarUsuario()
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

    private fun registrarUsuario() {
        binding.pbRegister.indeterminateDrawable.setColorFilter(Color.parseColor("#26bad3"), PorterDuff.Mode.MULTIPLY);
        binding.pbRegister.visibility=View.VISIBLE
        val email = binding.ietMail.text.toString()
        val password = binding.ietPassword.text.toString()

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                binding.pbRegister.visibility=View.GONE
                if (task.isSuccessful) {
                    Toast.makeText(context,"Usuario creado con exito",Toast.LENGTH_LONG).show()

                    findNavController().navigate(R.id.homeFragment)

                } else {
                    when (task.exception) {
                        is FirebaseAuthWeakPasswordException -> {
                            Toast.makeText(context,"La contraseña es débil, deben ser mínimo 6 caracteres.",Toast.LENGTH_LONG).show()
                        }
                        is FirebaseAuthInvalidCredentialsException -> {
                            Toast.makeText(context,"El correo electrónico es inválido",Toast.LENGTH_LONG).show()
                        }
                        is FirebaseAuthUserCollisionException -> {
                            Toast.makeText(context,"Ya existe un usuario con ese correo electrónico",Toast.LENGTH_LONG).show()
                        }
                        else -> {
                            Toast.makeText(context,"Hay un problema, intente de nuevo mas tarde",Toast.LENGTH_LONG).show()
                        }
                    }
                }
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