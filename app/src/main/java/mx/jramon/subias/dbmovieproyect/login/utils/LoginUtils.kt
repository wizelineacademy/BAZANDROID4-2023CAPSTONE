package mx.jramon.subias.dbmovieproyect.login.utils

import android.content.Context
import com.google.android.material.textfield.TextInputLayout
import mx.jramon.subias.dbmovieproyect.R

 val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()

fun isValidEmail(etEmail: TextInputLayout, context: Context):Boolean{
    val email = etEmail.editText?.text.toString()
    return if((email.isNotEmpty() && email.matches(emailPattern)))
        true
    else{
        etEmail.error = context.getString(R.string.sign_in_email_error)
        false
    }
}

 fun isValidPassword(etPass: TextInputLayout, context: Context):Boolean{
    return if(etPass.editText?.text.toString().isEmpty()){
        etPass.error = context.getString(R.string.sign_in_password_error)
        false
    }else
        true
}

 fun isValidConfirmPass(etConfirmPassword:TextInputLayout, pass:String, context: Context): Boolean{
     return if(etConfirmPassword.editText?.text.toString() != pass || etConfirmPassword.editText?.text.toString().isEmpty()){
         etConfirmPassword.error = context.getString(R.string.sign_up_error)
         false
     }else
         true
 }