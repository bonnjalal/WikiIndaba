package com.bonnjalal.wikiindaba.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bonnjalal.wikiindaba.R


@Composable
fun ProgramScreen(){
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFFA39274))) {

        val (logo) = createRefs()

        Row (modifier = Modifier
            .constrainAs(logo) {
                height = Dimension.wrapContent
                width = Dimension.fillToConstraints
                top.linkTo(parent.top, margin = 25.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }){

            Spacer(modifier = Modifier.fillMaxWidth(0.1f))
            Image(modifier = Modifier
                .width(120.dp)
                .height(35.dp)
                .align(Alignment.CenterVertically)
//            .size(width = 160.dp, height = 60.dp)
                , imageVector = ImageVector.vectorResource(id = R.drawable.logo_wikiindaba),
                contentDescription = "indaba logo", contentScale = ContentScale.FillBounds)

            Spacer(modifier = Modifier.fillMaxWidth(0.7f))
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.CenterVertically)) {
                Icon(painter = painterResource(id = R.drawable.material_symbols_logout),
                    contentDescription = "logout logo", tint = Color(0xFFF5EEDF)
                )
            }


        }

    }
}

@Preview
@Composable
fun PreviewProgamScreen(){
    ProgramScreen()
}