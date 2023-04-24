package mx.jramon.subias.dbmovieproyect.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.jramon.subias.dbmovieproyect.ui.White


@Composable
fun OutlineTextFieldComponent(
    hint:String,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable (() -> Unit)? = null,
    onValue:(String)->Unit
){

    var value by remember { mutableStateOf("") }

    Column(modifier) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp),
            value = value,
            colors = TextFieldDefaults.outlinedTextFieldColors(),
            singleLine = true,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            trailingIcon = trailingIcon,
            onValueChange = { text->
                text.let {
                    onValue(it)
                    value = text
                }
            },
            label = {
               Text(text = hint)
            }
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun OutlineTextFieldComponentPreview(){
    /*OutlineTextFieldComponent(
        "Correo Electronico"
    ){

    }*/
}