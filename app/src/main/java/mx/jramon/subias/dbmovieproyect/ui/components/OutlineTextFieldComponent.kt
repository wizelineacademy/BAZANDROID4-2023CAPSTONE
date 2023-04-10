package mx.jramon.subias.dbmovieproyect.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun OutlineTextFieldComponent(
    hint:String,
    modifier: Modifier = Modifier,
    onValue:(String)->Unit
){

    var value by remember { mutableStateOf("") }

    Column(modifier) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp),
            value = value,
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