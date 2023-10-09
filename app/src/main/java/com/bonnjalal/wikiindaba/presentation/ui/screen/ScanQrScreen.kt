package com.bonnjalal.wikiindaba.presentation.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bonnjalal.wikiindaba.R
import com.bonnjalal.wikiindaba.presentation.ui.MainViewModel
import com.slaviboy.composeunits.dh
import com.slaviboy.composeunits.dw
import com.slaviboy.composeunits.sh


@Composable
fun ScanQrScreen (openAndPopUp: (String, String) -> Unit,popup: () -> Unit, vm:MainViewModel = hiltViewModel()) {
    Column (modifier = Modifier
        .background(color = Color(0xFFFFFFFF))
        .fillMaxSize()) {


        Row (Modifier.padding(top = 0.02.dh)){
            Spacer(modifier = Modifier.fillMaxWidth(0.1f))
            Text(
                modifier = Modifier.fillMaxWidth(0.8f),
                text = "How to become an administrator on the English Wikipedia",
                style = TextStyle(
                    fontSize = 0.025.sh,
//                    lineHeight = 22.5.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                )
            )

            IconButton(
                modifier = Modifier.align(Alignment.Top),
                onClick = { popup() }) {
                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.go_back),
                    contentDescription = "back icon")
            }

        }

        Row (modifier = Modifier.padding(vertical = 0.01.dh)) {
            Spacer(modifier = Modifier.fillMaxWidth(0.1f))
            Icon(
                modifier = Modifier.padding(1.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.location_icon),
                contentDescription = "location icon",
                tint =Color(0xFFA1A1A1) )
            Text(
                modifier = Modifier.padding(horizontal = 0.016.dw),
                text = "Room 2",
                style = TextStyle(
                    fontSize = 0.012.sh,
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
                text = "22 Nov, 13:00 - 13:30",
                style = TextStyle(
                    fontSize = 0.012.sh,
//                    lineHeight = 17.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFFA1A1A1),
                )
            )
        }

        Spacer(modifier = Modifier.height(0.02.dh))
        Text(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .align(Alignment.CenterHorizontally),
//                .padding(top = 0.08.dp),
            text = "Add new attendee",
            style = TextStyle(
                fontSize = 0.022.sh,
//                lineHeight = 22.5.sp,
//                fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
            )
        )

        Spacer(modifier = Modifier.height(0.03.dh))
        Box (modifier = Modifier
            .height(0.05.dh)
            .fillMaxWidth(0.8f)
            .align(Alignment.CenterHorizontally)
//            .padding(top = 40.dp)
            .background(
                color = Color(0xFF531B1C),
                shape = RoundedCornerShape(size = 0.01.dh)
            )
            .clickable {
//                vm.onSignInClick()
            },
            contentAlignment = Alignment.Center) {

            Text(
                text = "With QR Code",
                style = TextStyle(
                    fontSize = 0.018.sh,
//                        fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                )
            )
        }
        Spacer(modifier = Modifier.height(0.016.dh))
        OutlinedButton(
            modifier = Modifier
//                    .offset(x = 0.07.dw, y = 0.2.dh)
                .height(0.05.dh)
                .fillMaxWidth(0.8f)
                .align(Alignment.CenterHorizontally)
            ,
            border = BorderStroke(color = Color(0xFF531B1C), width = 1.3.dp),
            shape = RoundedCornerShape(size = 0.01.dh),
            onClick = {

            }) {
            Text(
                text = "Manually",
                style = TextStyle(
                    fontSize = 0.018.sh,
//                        fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF531B1C),
                )
            )
        }
        /*Box (modifier = Modifier
            .height(0.05.dh)
            .fillMaxWidth(0.8f)
            .align(Alignment.CenterHorizontally)
//            .padding(top = 40.dp)
            .background(
                color = Color(0xFF531B1C),
                shape = RoundedCornerShape(size = 0.01.dh)
            )
            .clickable {
//                vm.onSignInClick()
            },
            contentAlignment = Alignment.Center) {

            Text(
                text = "Manually",
                style = TextStyle(
                    fontSize = 0.018.sh,
//                        fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                )
            )
        }*/

        Spacer(modifier = Modifier.height(0.024.dh))
        Text(modifier = Modifier
            .fillMaxWidth(0.8f)
            .align(Alignment.CenterHorizontally),
            text = "List of attendees",
            style = TextStyle(
                fontSize = 0.022.sh,
//                lineHeight = 22.5.sp,
//                fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
            )
        )

        Spacer(modifier = Modifier.height(0.016.dh))

        LazyColumn (modifier = Modifier
            .fillMaxWidth(0.8f)
            .align(Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy((-8).dp)){
            // Add 5 items
            items(10) { index ->

                Row(Modifier.padding(0.dp)){
                    Text(
                        modifier = Modifier.padding(end = 0.016.dw)
                        .fillMaxWidth(0.9f).align(Alignment.CenterVertically),
                        text = "Abdelilah Jalal",
                        style = TextStyle(
                            fontSize = 0.017.sh,
//                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF000000),
                        )
                    )

//                    Spacer(modifier = Modifier.fillMaxWidth(0.5f))

                    IconButton(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        onClick = { /*TODO*/ }) {
                       Icon(imageVector = ImageVector.vectorResource(R.drawable.delete_icon),
                           contentDescription = "delete icon",
                           tint = Color(0xFFA1A1A1))
                    }
                }
            }
        }


    }
}