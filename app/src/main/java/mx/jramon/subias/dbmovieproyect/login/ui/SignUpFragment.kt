package mx.jramon.subias.dbmovieproyect.login.ui

import android.widget.Toast
import androidx.fragment.app.activityViewModels
import mx.jramon.subias.dbmovieproyect.login.utils.isValidConfirmPass
import mx.jramon.subias.dbmovieproyect.login.utils.isValidEmail
import mx.jramon.subias.dbmovieproyect.login.utils.isValidPassword
import mx.jramon.subias.dbmovieproyect.R
import mx.jramon.subias.dbmovieproyect.base.BaseFragment
import mx.jramon.subias.dbmovieproyect.databinding.FragmentSignUpBinding
import mx.jramon.subias.dbmovieproyect.login.viewmodel.LoginViewModel

class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {

    private val vModel: LoginViewModel by activityViewModels()

    override fun getLayout() = R.layout.fragment_sign_up

    override fun initView() {

        vModel.validUser.observe(this){ userCreate->
            vModel.showLottie(false)

            if(!userCreate)
                Toast.makeText(requireContext(), "No se creo la cuenta",Toast.LENGTH_LONG).show()
        }

        vBind.btnSignUp.setOnClickListener {

            context?.let { context ->
                if(
                    isValidEmail(vBind.etEmail, context) &&
                    isValidPassword(vBind.etPass, context) &&
                    isValidConfirmPass(vBind.etConfirmPassword, vBind.etPass.editText?.text.toString(),context)
                ){
                    vModel.showLottie(true)
                    vModel.authCreateUser(vBind.etEmail.editText?.text.toString(), vBind.etPass.editText?.text.toString())
                }
            }
        }

    }
}