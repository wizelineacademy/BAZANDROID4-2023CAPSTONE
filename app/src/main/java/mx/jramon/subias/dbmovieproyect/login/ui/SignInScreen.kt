package mx.jramon.subias.dbmovieproyect.login.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import mx.jramon.subias.dbmovieproyect.R
import mx.jramon.subias.dbmovieproyect.login.ui.routes.LoginRoutes
import mx.jramon.subias.dbmovieproyect.login.utils.emailPattern
import mx.jramon.subias.dbmovieproyect.login.viewmodel.LoginViewModel
import mx.jramon.subias.dbmovieproyect.ui.components.OutlineTextFieldComponent
import mx.jramon.subias.dbmovieproyect.ui.Primary
import mx.jramon.subias.dbmovieproyect.ui.White
import mx.jramon.subias.dbmovieproyect.ui.components.ButtonComponent

@Composable
fun SignInScreen(loginViewModel: LoginViewModel, navController: NavHostController) {

    val context = LocalContext.current
    var email by rememberSaveable  { mutableStateOf("") }
    var password by rememberSaveable  { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }


    Column() {
        Column(
            modifier = Modifier.padding(top = 69.dp)
        ) {
            OutlineTextFieldComponent(
                hint = context.getString(R.string.email)
            ){
                email = it
            }

            OutlineTextFieldComponent(
                hint = context.getString(R.string.password),
                modifier = Modifier.padding(top = 24.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if(passwordVisible) painterResource(R.drawable.ic_visibility)
                    else painterResource(R.drawable.ic_visibility_off)

                    val description = if(passwordVisible)"Hide password" else "password"
                    IconButton(onClick = {passwordVisible = !passwordVisible}){
                        Icon(image, contentDescription = description)
                    }
                }
            ){
                password = it
            }

        }

        Column(Modifier.weight(1f)){}

        ButtonComponent(
            text = context.getString(R.string.sign_in_title),
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            println(email)
            println(password)

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(context, "Ingresa información valida", Toast.LENGTH_SHORT).show()
            }else if(!email.matches(emailPattern))
                Toast.makeText(context, "correo invalido", Toast.LENGTH_SHORT).show()
            else
                loginViewModel.validateSignInUser(email,password)
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text =context.getString(R.string.sign_in_no_account),
                color = White

            )
            Text(
                text = context.getString(R.string.sign_in_register),
                color = Primary,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable {
                        navController.navigate(LoginRoutes.SignUp.routes)
                    }
            )
        }
    }
}