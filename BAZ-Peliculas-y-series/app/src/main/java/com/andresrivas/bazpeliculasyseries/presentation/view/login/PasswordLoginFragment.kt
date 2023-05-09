package com.andresrivas.bazpeliculasyseries.presentation.view.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.andresrivas.bazpeliculasyseries.R
import com.andresrivas.bazpeliculasyseries.databinding.FragmentPasswordLoginBinding
import com.andresrivas.bazpeliculasyseries.presentation.view.MainActivity
import com.andresrivas.bazpeliculasyseries.presentation.viewmodel.PasswordLoginViewModel
import com.andresrivas.bazpeliculasyseries.tools.AuthenticationManager
import com.andresrivas.bazpeliculasyseries.tools.SignInProviderType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PasswordLoginFragment : Fragment() {

    private lateinit var binding: FragmentPasswordLoginBinding
    private val viewModel: PasswordLoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPasswordLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finishAffinity()
        }

        binding.emailEditText.setOnFocusChangeListener { _, _ ->
            if (shouldHideHeaderImage()) hideHeaderImage()
        }

        binding.passwordEditText.setOnFocusChangeListener { _, _ ->
            if (shouldHideHeaderImage()) hideHeaderImage()
        }

        binding.googleLoginButton.setOnClickListener {
            AuthenticationManager.Builder(
                registry = requireActivity().activityResultRegistry,
                context = requireActivity().applicationContext,
            ).addSignInProviderType(
                SignInProviderType.GoogleSignIn,
            ).addSuccessCallback {
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finishAffinity()
            }.addFailCallback {
                Toast.makeText(requireContext(), "Failure :'( $it", Toast.LENGTH_SHORT).show()
            }.build().launch()
        }

        binding.emailLoginButton.setOnClickListener {
            AuthenticationManager.Builder(
                registry = requireActivity().activityResultRegistry,
                context = requireActivity().applicationContext,
            ).addSignInProviderType(
                SignInProviderType.EmailSignIn,
            ).addSuccessCallback {
                Toast.makeText(
                    requireContext(),
                    "Login success! ${it.email}",
                    Toast.LENGTH_SHORT,
                ).show()
            }.addFailCallback {
                Toast.makeText(requireContext(), "Failure :'( $it", Toast.LENGTH_SHORT).show()
            }.build().launch()
        }
    }

    private fun shouldHideHeaderImage() = binding.headerImage.visibility == View.VISIBLE

    private fun hideHeaderImage() {
        binding.headerImage.startAnimation(
            AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up_animation).apply {
                fillAfter = true
                setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationEnd(p0: Animation?) {
                        binding.headerImage.visibility = View.GONE
                        binding.imageViewGuideline.setGuidelinePercent(0.10f)
                        binding.loginFormBottomGuideline.setGuidelinePercent(0.60f)
                    }
                    override fun onAnimationStart(p0: Animation?) {}
                    override fun onAnimationRepeat(p0: Animation?) {}
                })
            },
        )
    }
}
