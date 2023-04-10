package mx.jramon.subias.dbmovieproyect.login.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.jramon.subias.dbmovieproyect.R
import mx.jramon.subias.dbmovieproyect.login.viewmodel.LoginViewModel
import mx.jramon.subias.dbmovieproyect.ui.components.OutlineTextFieldComponent
import mx.jramon.subias.dbmovieproyect.ui.Primary
import mx.jramon.subias.dbmovieproyect.ui.White
import mx.jramon.subias.dbmovieproyect.ui.components.ButtonComponent

@Composable
fun SignInScreen(loginViewModel: LoginViewModel) {

    val context = LocalContext.current
    Column() {
        Column(
            modifier = Modifier.padding(top = 69.dp)
        ) {
            OutlineTextFieldComponent(
                hint = context.getString(R.string.email)
            ){

            }

            OutlineTextFieldComponent(
                hint = context.getString(R.string.password),
                modifier = Modifier.padding(top = 24.dp)
            ){

            }

        }

        Column(Modifier.weight(1f)){}

        ButtonComponent(
            text = context.getString(R.string.sign_in_title),
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            
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
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}