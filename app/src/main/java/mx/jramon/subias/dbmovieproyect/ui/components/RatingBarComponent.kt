package mx.jramon.subias.dbmovieproyect.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import mx.jramon.subias.dbmovieproyect.R
import mx.jramon.subias.dbmovieproyect.ui.Primary

@Composable
 fun RatingBarComponent(
    rated:Float,
    modifier: Modifier = Modifier
) {
    val starNumberInteger = rated.toInt()
    val isHalfStar = (rated - starNumberInteger.toFloat()) != 0.0f
    val starBorder = (5f - starNumberInteger).toInt()
    val starFree = if(isHalfStar) starBorder -1 else starBorder


    Row(modifier) {
        for (item:Int in 1..starNumberInteger){
            Image(
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = "star" ,
                colorFilter = ColorFilter.tint(Color.Yellow)
            )
        }
        if(isHalfStar)
            Image(
                painter = painterResource(id = R.drawable.ic_star_half),
                contentDescription = "halfStar",
                colorFilter = ColorFilter.tint(Color.Yellow)
            )


        for(item:Int in 1..starFree.toInt())
            Image(painter = painterResource(id = R.drawable.ic_star_border), contentDescription = "borderStart")
    }
}

@Preview(showSystemUi = true)
@Composable
fun RatinBarPreview(){
    RatingBarComponent(5f)
}
