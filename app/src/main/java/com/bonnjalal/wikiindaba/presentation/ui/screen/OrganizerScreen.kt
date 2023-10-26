package com.bonnjalal.wikiindaba.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bonnjalal.wikiindaba.R
import com.bonnjalal.wikiindaba.common.compose.CustomTextField
import com.bonnjalal.wikiindaba.presentation.model.Attendee
import com.bonnjalal.wikiindaba.presentation.model.Organizer
import com.bonnjalal.wikiindaba.presentation.state.DataState
import com.bonnjalal.wikiindaba.presentation.state.ProgramStartState
import com.bonnjalal.wikiindaba.presentation.ui.MainStateEvent
import com.bonnjalal.wikiindaba.presentation.ui.MainViewModel
import com.slaviboy.composeunits.dh
import com.slaviboy.composeunits.dw
import com.slaviboy.composeunits.sh


@Composable
fun OrganizerScreen(modifier: Modifier= Modifier, vm: MainViewModel){

    val showOrganizers by remember {vm.showAttendees}
    val uiState by vm.searchOrganizerState

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
            SearchOrganizerField(uiState, vm::onOrganizerSearchChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.04.dh)
//                    .defaultMinSize(minHeight = 20.dp, minWidth = 150.dp)
                    .align(Alignment.CenterHorizontally))

            Spacer(Modifier.height(0.03.dh))

            var organizerItem by remember { mutableStateOf(Attendee("","","","","","","","")) }
            var showOrganizerDialog by remember {mutableStateOf(false)}
            if (showOrganizers) {
                LazyColumn(modifier = Modifier.align(Alignment.CenterHorizontally)) {

                    items((vm.dataStateAttendee.value as DataState.Success).data.filter {
                        it.role.contains("Speaker", ignoreCase = true) &&
                                it.name.contains(uiState, ignoreCase = true)
//                        it.role.contains("Core Team organizer", ignoreCase = true) ||
//                                it.role.contains("Organizer - Fiscal Sponsor", ignoreCase = true)
//                                || it.role.contains("Organizer", ignoreCase = true)
                                                                                        
                    },
                        key = {it.id}) { organizer ->
//                        val time = vm.getDateTime(program.startTime, program.endTime)

                        OrganizerCard(
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
                                )
                                .clickable {
                                    organizerItem = organizer
                                    showOrganizerDialog = true
                                },
                            organizer =  organizer
                        )
                        Spacer(Modifier.height(0.01.dh))
                    }


                }
                DialogWithImage(
                    showDialog = showOrganizerDialog,
                    onDismissRequest = {
                        showOrganizerDialog = false
                    },
                    organizer = organizerItem,
                    vm = vm
                )

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
fun SearchOrganizerField(value: String,  onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {

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
            text = "Look for a Speaker ...",
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
fun OrganizerCard(modifier: Modifier, organizer:Attendee){
    Row (modifier = modifier.padding(start = 0.016.dw, top = 0.016.dw)
//        .padding(horizontal = 8.dp)
    ) {
//        val imgUrl = if (organizer.imgUrl == "") "https://upload.wikimedia.org/wikipedia/commons/f/f8/Profile_photo_placeholder_square.svg"
//                    else organizer.imgUrl
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(
                    if (organizer.imgUrl == "") "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f8/Profile_photo_placeholder_square.svg/640px-Profile_photo_placeholder_square.svg.png"
                    else organizer.imgUrl
                )
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.placeholder),
            contentDescription = "Organizer Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(0.1.dh)
                .align(Alignment.CenterVertically)
        )
        Column {
            Text(
                modifier = Modifier.padding(start = 0.03.dw, top = 8.dp, end = 0.03.dw),
                text = organizer.name,
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
                text = organizer.role,
                style = TextStyle(
                    fontSize = 0.013.sh,
//                lineHeight = 17.sp,
//                fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF531B1C),
                )
            )
            /*Row (modifier = Modifier.padding(horizontal = 0.016.dh, vertical = 0.008.dh)) {
                Icon(
                    modifier = Modifier.padding(1.dp).align(Alignment.CenterVertically),
                    imageVector = Icons.Filled.PersonPin,//ImageVector.vectorResource(id = R.drawable.location_icon),
                    contentDescription = "location icon",
                    tint = Color(0xFFA1A1A1) )
                Text(
                    modifier = Modifier.padding(horizontal = 0.016.dw).align(Alignment.CenterVertically),
                    text = organizer.username,
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
                    text = organizer.email,
                    style = TextStyle(
                        fontSize = 0.011.sh,
//                    lineHeight = 17.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFFA1A1A1),
                    )
                )
            }*/
        }


    }
}

@Composable
fun DialogWithImage(
    showDialog : Boolean = false,
    onDismissRequest: () -> Unit,
//    onConfirmation: () -> Unit,
    organizer: Attendee,
    vm:MainViewModel
) {

    if (showDialog){
        Dialog(onDismissRequest = { onDismissRequest() },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)) {
            // Draw a rectangle shape with rounded corners inside the dialog
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
//                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(0.01.dh),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                ) {
                    Box {

                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(
                                    if (organizer.imgUrl == "") "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f8/Profile_photo_placeholder_square.svg/640px-Profile_photo_placeholder_square.svg.png"
                                    else organizer.imgUrl
                                )
                                .crossfade(true)
                                .build(),
                            placeholder = painterResource(R.drawable.placeholder),
                            contentDescription = "Organizer Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(0.35.dh)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(0.01.dh))
                        )
                        Text(
                            text = organizer.name,
                            modifier = Modifier.padding(0.008.dh).align(Alignment.TopStart),
                        )
                    }

                    Text(
                        text = "Presentations:",
                        modifier = Modifier.padding(0.016.dh),
                    )
                    var cardColor by remember { mutableStateOf(Color(0xFFF5EEDF))}
                    LazyColumn(modifier = Modifier
                        .padding(0.008.dh)
                        .height(0.24.dh)
                        .align(Alignment.CenterHorizontally)) {

                        items((vm.dataStateProgram.value as DataState.Success).data.filter {
                            it.authorsName.contains(organizer.name, ignoreCase = true)
                        },
                            key = {it.id}) { program ->
//                        val time = vm.getDateTime(program.startTime, program.endTime)
                            val programState = vm.compareDates(program.startTime, program.endTime)
                            cardColor = when (programState){
                                ProgramStartState.NOW -> Color(0xFFDAF5D6)
                                ProgramStartState.SOON -> Color(0xFFF2F3CF)
                                ProgramStartState.NONE -> Color(0xFFF5EEDF)
                            }

                            ProgramCard(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally)
                                    .background(
                                        color = cardColor,
                                        shape = RoundedCornerShape(size = 0.012.dh)
                                    ),
                                title = program.title,
                                room = program.room,
                                time = vm.getDateTime(program.startTime, program.endTime),
                                authors = program.authors
                            )
                            Spacer(Modifier.height(0.008.dh))
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        TextButton(
                            onClick = { onDismissRequest() },
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.Bottom),
                        ) {
                            Text("Dismiss")
                        }
                    }
                }
            }
        }
    }

}