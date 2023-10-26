package com.bonnjalal.wikiindaba.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Room
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bonnjalal.wikiindaba.R
import com.bonnjalal.wikiindaba.common.compose.CustomTextField
import com.bonnjalal.wikiindaba.presentation.model.Attendee
import com.bonnjalal.wikiindaba.presentation.state.DataState
import com.bonnjalal.wikiindaba.presentation.ui.MainStateEvent
import com.bonnjalal.wikiindaba.presentation.ui.MainViewModel
import com.slaviboy.composeunits.dh
import com.slaviboy.composeunits.dw
import com.slaviboy.composeunits.sh


@Composable
fun AttendeeScreen(modifier: Modifier = Modifier, vm: MainViewModel){

    val showAttendees by remember {vm.showAttendees}
    val uiState by vm.searchAttendeeState

    LaunchedEffect(key1 = Unit, block = {
//        vm.syncAttendance()
        vm.setStateEvent(MainStateEvent.GetAttendeesEvent)
//        vm.setStateEvent(MainStateEvent.GetOrganizersEvent)
//        if (!userStateAnonymous){
//            vm.setStateEvent(MainStateEvent.GetAttendeesEvent)
//        }
    })

    Column (modifier = modifier
//            .fillMaxWidth(0.85f)
//            .background(
//                color = Color.White,
//                shape = RoundedCornerShape(topStart = 0.035.dh, topEnd = 0.035.dh)
//            )
    ) {

        Spacer(Modifier.height(0.035.dh))
        SearchAttendeeField(uiState, vm::onAttendeeSearchChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(0.04.dh)
//                    .defaultMinSize(minHeight = 20.dp, minWidth = 150.dp)
                .align(Alignment.CenterHorizontally))

        Spacer(Modifier.height(0.03.dh))

        if (showAttendees) {
            LazyColumn(modifier = Modifier.align(Alignment.CenterHorizontally)) {

//                items((vm.dataStateAttendee.value as DataState.Success).data.filter {
                items(vm.attendeesList.filter {
                    it.name.contains(uiState, ignoreCase = true) ||
                            it.room.contains(uiState, ignoreCase = true) || it.phoneNumber.contains(uiState, ignoreCase = true)
                            || it.role.contains(uiState, ignoreCase = true) || it.username.contains(uiState, ignoreCase = true)
                            || it.email.contains(uiState, ignoreCase = true)
                },
                    key = {it.id}) { attendee ->
//                        val time = vm.getDateTime(program.startTime, program.endTime)

                    AttendeeCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
//                                .clickable {
//                                    vm.program.value = program
//                                    vm.setStateEvent(MainStateEvent.GetAttendanceEvent)
////                                    if (!userStateAnonymous) navigate(SCAN_QR_SCREEN)
//                                }
                            .background(
                                color = Color(0xFFF5EEDF),
                                shape = RoundedCornerShape(size = 0.012.dh)
                            ),
                        attendee =  attendee
                    )
                    Spacer(Modifier.height(0.01.dh))
                }

            }
        }else {
            Column (modifier = Modifier.align(Alignment.CenterHorizontally)){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color(0xFFF5EEDF)
                )
                Text(modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Loading organizers ...", color = Color(0xFF531B1C),
                    fontSize = 0.013.sh)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAttendeeField(value: String,  onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {

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
            text = "Look for an attendee ...",
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
fun AttendeeCard(modifier: Modifier, attendee: Attendee){
    Row (modifier = modifier.padding(start = 0.016.dw, top = 0.016.dw)
//        .padding(horizontal = 8.dp)
    ) {
//        val imgUrl = if (organizer.imgUrl == "") "https://upload.wikimedia.org/wikipedia/commons/f/f8/Profile_photo_placeholder_square.svg"
//                    else organizer.imgUrl
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(
                    if (attendee.imgUrl == "") "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f8/Profile_photo_placeholder_square.svg/640px-Profile_photo_placeholder_square.svg.png"
                    else attendee.imgUrl
                )
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder),
            contentDescription = "Organizer Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(CircleShape).size(0.1.dh).align(Alignment.CenterVertically)
        )
        Column {
            Text(
                modifier = Modifier.padding(start = 0.03.dw, top = 8.dp, end = 0.03.dw),
                text = attendee.name,
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
                text = attendee.role,
                style = TextStyle(
                    fontSize = 0.013.sh,
//                lineHeight = 17.sp,
//                fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF531B1C),
                )
            )
            Row (modifier = Modifier.padding(start = 0.016.dh,end = 0.016.dh, top = 0.008.dh)) {
                Icon(
                    modifier = Modifier.padding(1.dp).align(Alignment.CenterVertically),
                    imageVector = Icons.Filled.PersonPin,//ImageVector.vectorResource(id = R.drawable.location_icon),
                    contentDescription = "location icon",
                    tint = Color(0xFFA1A1A1) )
                Text(
                    modifier = Modifier.padding(horizontal = 0.016.dw).align(Alignment.CenterVertically),
                    text = attendee.username,
                    style = TextStyle(
                        fontSize = 0.011.sh,
//                    lineHeight = 17.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFFA1A1A1),
                    )
                )

                Icon(
                    modifier = Modifier.padding(1.dp).align(Alignment.CenterVertically),
                    imageVector = Icons.Filled.PhoneAndroid,//ImageVector.vectorResource(id = R.drawable.clock_icon),
                    contentDescription = "location icon",
                    tint = Color(0xFFA1A1A1)
                )
                Text(
                    modifier = Modifier.padding(horizontal = 0.016.dw).align(Alignment.CenterVertically),
                    text = attendee.phoneNumber,
                    style = TextStyle(
                        fontSize = 0.011.sh,
//                    lineHeight = 17.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFFA1A1A1),
                    )
                )
            }
            Row (modifier = Modifier.padding(horizontal = 0.016.dh, vertical = 0.001.dh)) {
                Icon(
                    modifier = Modifier.padding(1.dp).align(Alignment.CenterVertically),
                    imageVector = Icons.Filled.Room,//ImageVector.vectorResource(id = R.drawable.location_icon),
                    contentDescription = "location icon",
                    tint = Color(0xFFA1A1A1) )
                Text(
                    modifier = Modifier.padding(horizontal = 0.016.dw).align(Alignment.CenterVertically),
                    text = attendee.room,
                    style = TextStyle(
                        fontSize = 0.011.sh,
//                    lineHeight = 17.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFFA1A1A1),
                    )
                )

                Icon(
                    modifier = Modifier.padding(1.dp).align(Alignment.CenterVertically),
                    imageVector = Icons.Filled.Email,//ImageVector.vectorResource(id = R.drawable.clock_icon),
                    contentDescription = "location icon",
                    tint = Color(0xFFA1A1A1)
                )
                Text(
                    modifier = Modifier.padding(horizontal = 0.016.dw).align(Alignment.CenterVertically),
                    text = attendee.email,
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
}
