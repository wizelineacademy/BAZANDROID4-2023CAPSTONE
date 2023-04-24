package mx.jramon.subias.dbmovieproyect.login.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import mx.jramon.subias.dbmovieproyect.R
import mx.jramon.subias.dbmovieproyect.login.utils.emailPattern
import mx.jramon.subias.dbmovieproyect.login.viewmodel.LoginViewModel
import mx.jramon.subias.dbmovieproyect.ui.components.ButtonComponent
import mx.jramon.subias.dbmovieproyect.ui.components.OutlineTextFieldComponent

@Composable
fun  SignUpScreen(loginViewModel: LoginViewModel, navController: NavHostController) {

    val context = LocalContext.current
    var email by rememberSaveable  { mutableStateOf("") }
    var password by rememberSaveable  { mutableStateOf("") }
    var passwordConfirm by rememberSaveable  { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Column() {
        OutlineTextFieldComponent(
            hint = context.getString(R.string.email),
            modifier = Modifier.padding(top = 64.dp)
             ){
            email = it
        }

        OutlineTextFieldComponent(
            hint = context.getString(R.string.password),
            modifier = Modifier.padding(top = 24.dp)
        ){
            password = it
        }

        OutlineTextFieldComponent(
            hint = context.getString(R.string.confirm_password),
            modifier = Modifier.padding(top = 24.dp)
        ){
            passwordConfirm = it
        }

        Column(Modifier.weight(1f)){}

        ButtonComponent(
            text = context.getString(R.string.sign_up_continue),
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(context, "Ingresa información valida", Toast.LENGTH_SHORT).show()
            }else if(!email.matches(emailPattern))
                Toast.makeText(context, "correo invalido", Toast.LENGTH_SHORT).show()
            else if(password == passwordConfirm)
                Toast.makeText(context, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show()
            else
                loginViewModel.authCreateUser(email,password)

        }
    }
}