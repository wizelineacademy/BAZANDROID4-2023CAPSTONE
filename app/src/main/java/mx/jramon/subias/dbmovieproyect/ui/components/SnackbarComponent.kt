package mx.jramon.subias.dbmovieproyect.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.jramon.subias.dbmovieproyect.R
import mx.jramon.subias.dbmovieproyect.ui.SuccessBar


@Composable
fun SnackbarComponent(
    text:String,
    colorBackground:Color,
    modifier: Modifier = Modifier,
    onClickClose:()->Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(35.dp)
            .background(color = SuccessBar)
            .then(modifier)
    ) {
        Text(
            text = text,
            color = colorBackground,
            modifier = Modifier
                .padding(start = 24.dp, top = 16.dp)
                .clickable { onClickClose() }
        )

        Box(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = "close",
            modifier = Modifier.padding(end = 8.dp, top = 8.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun SnackbarComponentPreview(){
    val context = LocalContext.current
    SnackbarComponent(
        text = "Conectado",
        colorBackground = SuccessBar,
    ){

    }
}