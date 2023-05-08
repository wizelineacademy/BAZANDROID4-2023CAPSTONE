package mx.jramon.subias.dbmovieproyect.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.jramon.subias.dbmovieproyect.ui.Primary
import mx.jramon.subias.dbmovieproyect.ui.White


@Composable
fun ButtonComponent(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(modifier = modifier) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Primary
            ),
            content = {
                Text(
                    text = text,
                    color = White,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )
            },
            onClick = { onClick() }
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun ButtonComponentPreview() {
    ButtonComponent(text = "Continuar") {

    }
}