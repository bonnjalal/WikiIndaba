package com.bonnjalal.wikiindaba.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.bonnjalal.wikiindaba.R
import com.bonnjalal.wikiindaba.common.PROGRAM_SCREEN
import com.bonnjalal.wikiindaba.common.SCAN_QR_SCREEN
import com.bonnjalal.wikiindaba.common.compose.CustomTextField
import com.bonnjalal.wikiindaba.presentation.state.DataState
import com.bonnjalal.wikiindaba.presentation.ui.MainViewModel
import com.slaviboy.composeunits.dh
import com.slaviboy.composeunits.dw
import com.slaviboy.composeunits.sh


@Composable
fun ProgramScreen(navigate: (String) -> Unit,logout:(String) -> Unit, vm: MainViewModel = hiltViewModel()){

    val uiState by vm.searchProgramState
    val userStateAnonymous by vm.userState.collectAsState(initial = true)
    val programState = vm.dataStateProgram


    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFFA39274))) {

        val (logo, col1) = createRefs()

        Row (modifier = Modifier
            .constrainAs(logo) {
                height = Dimension.wrapContent
                width = Dimension.fillToConstraints
                top.linkTo(parent.top, margin = 0.008.dh)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }){

            Spacer(modifier = Modifier.fillMaxWidth(0.1f))
            Image(modifier = Modifier
                .width(0.25.dw)
                .height(0.035.dh)
                .align(Alignment.CenterVertically)
//            .size(width = 160.dp, height = 60.dp)
                , imageVector = ImageVector.vectorResource(id = R.drawable.logo_wikiindaba),
                contentDescription = "indaba logo", contentScale = ContentScale.FillBounds)

            Spacer(modifier = Modifier.fillMaxWidth(0.7f))
            IconButton(onClick = { vm.onSignOut(logout) }, modifier = Modifier.align(Alignment.CenterVertically)) {
                Icon(painter = painterResource(id = R.drawable.material_symbols_logout),
                    contentDescription = "logout logo", tint = Color(0xFFF5EEDF)
                )
            }
        }

        Column (modifier = Modifier
//            .fillMaxWidth(0.85f)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 0.035.dh, topEnd = 0.035.dh)
            )
            .constrainAs(col1) {
                top.linkTo(logo.bottom, margin = 0.015.dh)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            }) {

            Spacer(Modifier.height(35.dp))
            Row (modifier = Modifier
                .fillMaxWidth(0.85f)
                .align(Alignment.CenterHorizontally)) {
//                Spacer(Modifier.fillMaxWidth(0.1f))
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = "Hello, There",
                    style = TextStyle(
                        fontSize = 0.025.sh,
//                        lineHeight = 17.sp,
//                        fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    )
                )

                Image(
                    modifier = Modifier
                        .padding(start = 0.008.dp)
                        .height(0.025.dh)
                        .width(0.050.dw)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = R.drawable.emoji_waving_hand_sign),
                    contentDescription = "image description",
                    contentScale = ContentScale.None
                )
            }

            Spacer(Modifier.height(0.035.dh))
            SearchField(uiState, vm::onProgramSearchChange,
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(0.04.dh)
//                    .defaultMinSize(minHeight = 20.dp, minWidth = 150.dp)
                    .align(Alignment.CenterHorizontally))

            Spacer(Modifier.height(0.03.dh))
            LazyColumn (modifier = Modifier.align(Alignment.CenterHorizontally)){
                // Add a single item
//                item {
//                    Text(text = "First item")
//                }

                // Add 5 items

                items((programState.value as DataState.Success).data){ program ->
                    val time = vm.getDateTime(program.startTime, program.endTime)
                    ProgramCard(modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            if (!userStateAnonymous) navigate(SCAN_QR_SCREEN)
                        },
                        title = program.title,
                        room = program.room,
                        time = time,
                        authors = program.authors)
                    Spacer(Modifier.height(0.01.dh))
                }
                /*items(10) {
                    ProgramCard(modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            if (!userStateAnonymous) navigate(SCAN_QR_SCREEN)
                        })
                    Spacer(Modifier.height(0.01.dh))

                }*/

                // Add another single item
//                item {
//                    Text(text = "Last item")
//                }
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(value: String,  onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {

    CustomTextField(modifier = modifier
//                .padding(top = 60.dp)
//                .height(52.dp),
            ,
        singleLine = true,
        leadingIcon = {
                      Icon(imageVector = ImageVector.vectorResource(id = R.drawable.search_icon),
                          contentDescription = "search icon")
        },
        placeholder = { Text(
            text = "Look for a presentation ...",
            style = TextStyle(
//                fontSize = 13.sp,
//                lineHeight = 17.sp,
//                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(300),
                color = Color(0xFF6D6D6D),
            )
        ) },
        colors = TextFieldDefaults.textFieldColors(containerColor =  Color(0xFFF3F3F3),
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(size = 10.dp),
        value = value,
        textStyle = TextStyle(
            fontSize = 14.sp,
//                        fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight(400),
            color = Color(0xFFA39274),
        ) , onValueChange = {
            onNewValue(it)
        })
}

@Composable
fun ProgramCard(modifier: Modifier, title:String, authors:String, room:String, time:String){
    Column (modifier = modifier
//        .padding(horizontal = 8.dp)
        .background(color = Color(0xFFF5EEDF), shape = RoundedCornerShape(size = 0.012.dh)) ) {

        Text(
            modifier = Modifier.padding(start = 0.03.dw, top = 8.dp, end = 0.03.dw),
            text = title,
            style = TextStyle(
                fontSize = 0.016.sh,
//                lineHeight = 17.sp,
//                fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight(500),
                color = Color(0xFF531B1C),
            )
        )
        Text(
            modifier = Modifier.padding(start = 0.03.dw, top = 0.004.dh, end = 0.03.dw),
            text = authors,
            style = TextStyle(
                fontSize = 0.013.sh,
//                lineHeight = 17.sp,
//                fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight(400),
                color = Color(0xFF531B1C),
            )
        )
        Row (modifier = Modifier.padding(horizontal = 0.016.dh, vertical = 0.008.dh)) {
            Icon(
                modifier = Modifier.padding(1.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.location_icon),
                contentDescription = "location icon",
                tint =Color(0xFFA1A1A1) )
            Text(
                modifier = Modifier.padding(horizontal = 0.016.dw),
                text = room,
                style = TextStyle(
                    fontSize = 0.011.sh,
//                    lineHeight = 17.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFFA1A1A1),
                )
            )

            Icon(
                modifier = Modifier.padding(1.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.clock_icon),
                contentDescription = "location icon",
                tint = Color(0xFFA1A1A1))
            Text(
                modifier = Modifier.padding(horizontal = 0.016.dw),
                text = time,
                style = TextStyle(
                    fontSize = 0.011.sh,
//                    lineHeight = 17.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFFA1A1A1),
                )
            )
        }
    }
}
@Preview
@Composable
fun PreviewProgamScreen(){
    ProgramScreen({ str1 -> },{})
}