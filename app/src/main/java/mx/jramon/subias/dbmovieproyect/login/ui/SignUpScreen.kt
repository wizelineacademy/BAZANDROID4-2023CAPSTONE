package mx.jramon.subias.dbmovieproyect.login.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.jramon.subias.dbmovieproyect.R
import mx.jramon.subias.dbmovieproyect.ui.components.ButtonComponent
import mx.jramon.subias.dbmovieproyect.ui.components.OutlineTextFieldComponent



@Preview(showSystemUi = true)
@Composable
fun  SignUpScreen () {

    val context = LocalContext.current

    Column() {
        OutlineTextFieldComponent(
            hint = context.getString(R.string.email),
            modifier = Modifier.padding(top = 64.dp)
             ){

        }

        OutlineTextFieldComponent(
            hint = context.getString(R.string.password),
            modifier = Modifier.padding(top = 24.dp)
        ){

        }

        OutlineTextFieldComponent(
            hint = context.getString(R.string.confirm_password),
            modifier = Modifier.padding(top = 24.dp)
        ){

        }

        Column(Modifier.weight(1f)){}

        ButtonComponent(
            text = context.getString(R.string.sign_up_continue),
            modifier = Modifier.padding(bottom = 24.dp)
        ) {
            
        }
    }
}