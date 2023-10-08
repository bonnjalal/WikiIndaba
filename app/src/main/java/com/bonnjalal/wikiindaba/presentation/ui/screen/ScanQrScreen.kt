package com.bonnjalal.wikiindaba.presentation.ui.screen

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


@Composable
fun ScanQrScreen (openAndPopUp: (String, String) -> Unit,popup: () -> Unit, vm:MainViewModel = hiltViewModel()) {
    Column (modifier = Modifier
        .background(color = Color(0xFFFFFFFF))
        .fillMaxSize()) {


        Row (Modifier.padding(top = 16.dp)){
            Spacer(modifier = Modifier.fillMaxWidth(0.1f))
            Text(
                modifier = Modifier.fillMaxWidth(0.8f),
                text = "How to become an administrator on the English Wikipedia",
                style = TextStyle(
                    fontSize = 24.sp,
                    lineHeight = 22.5.sp,
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

        Row (modifier = Modifier.padding(vertical = 8.dp)) {
            Spacer(modifier = Modifier.fillMaxWidth(0.1f))
            Icon(
                modifier = Modifier.padding(1.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.location_icon),
                contentDescription = "location icon",
                tint =Color(0xFFA1A1A1) )
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = "Room 2",
                style = TextStyle(
                    fontSize = 11.sp,
                    lineHeight = 17.sp,
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
                modifier = Modifier.padding(horizontal = 8.dp),
                text = "22 Nov, 13:00 - 13:30",
                style = TextStyle(
                    fontSize = 11.sp,
                    lineHeight = 17.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFFA1A1A1),
                )
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp),
            text = "Add new attendees",
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 22.5.sp,
//                fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
            )
        )

        Spacer(modifier = Modifier.height(25.dp))
        Box (modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(0.8f)
            .align(Alignment.CenterHorizontally)
//            .padding(top = 40.dp)
            .background(
                color = Color(0xFF531B1C),
                shape = RoundedCornerShape(size = 10.dp)
            )
            .clickable {
//                vm.onSignInClick()
            },
            contentAlignment = Alignment.Center) {

            Text(
                text = "With QR Code",
                style = TextStyle(
                    fontSize = 17.sp,
//                        fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                )
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Box (modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(0.8f)
            .align(Alignment.CenterHorizontally)
//            .padding(top = 40.dp)
            .background(
                color = Color(0xFF531B1C),
                shape = RoundedCornerShape(size = 10.dp)
            )
            .clickable {
//                vm.onSignInClick()
            },
            contentAlignment = Alignment.Center) {

            Text(
                text = "Manually",
                style = TextStyle(
                    fontSize = 17.sp,
//                        fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(modifier = Modifier
            .fillMaxWidth(0.8f)
            .align(Alignment.CenterHorizontally),
            text = "List of attendees",
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 22.5.sp,
//                fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        LazyColumn (modifier = Modifier
            .fillMaxWidth(0.8f)
            .align(Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy((-8).dp)){
            // Add 5 items
            items(10) { index ->

                Row(Modifier.padding(0.dp)){
                    Text(
                        modifier = Modifier.padding(end = 8.dp)
                        .fillMaxWidth(0.9f).align(Alignment.CenterVertically),
                        text = "Abdelilah Jalal",
                        style = TextStyle(
                            fontSize = 16.sp,
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