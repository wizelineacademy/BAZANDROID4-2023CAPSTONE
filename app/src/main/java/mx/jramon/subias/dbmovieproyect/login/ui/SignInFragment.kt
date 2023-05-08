package mx.jramon.subias.dbmovieproyect.login.ui

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import mx.jramon.subias.dbmovieproyect.login.utils.isValidEmail
import mx.jramon.subias.dbmovieproyect.login.utils.isValidPassword
import mx.jramon.subias.dbmovieproyect.login.viewmodel.LoginViewModel
import mx.jramon.subias.dbmovieproyect.R
import mx.jramon.subias.dbmovieproyect.base.BaseFragment
import mx.jramon.subias.dbmovieproyect.databinding.FragmentSignInBinding

class SignInFragment : BaseFragment<FragmentSignInBinding>() {

    private val vModel: LoginViewModel by activityViewModels()

    override fun getLayout() = R.layout.fragment_sign_in

    override fun initView() {

        vBind.tvRegister.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }

        vBind.inputLyPassword.editText?.addTextChangedListener {vBind.inputLyEmail.error = null}
        vBind.inputLyPassword.editText?.addTextChangedListener {vBind.inputLyPassword.error = null}

        vBind.btnSignIn.setOnClickListener {
            if(
                isValidEmail(vBind.inputLyEmail, requireContext()) &&
                isValidPassword(vBind.inputLyPassword, requireContext())
            ){
                   vModel.showLottie(true)
                   vModel.validateSignInUser(vBind.inputLyEmail.editText?.text.toString(), vBind.inputLyPassword.editText?.text.toString())
            }
        }
    }
}