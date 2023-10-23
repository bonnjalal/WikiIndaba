package com.bonnjalal.wikiindaba.presentation.ui.screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bonnjalal.wikiindaba.R
import com.bonnjalal.wikiindaba.common.compose.CustomTextField
import com.bonnjalal.wikiindaba.common.snackbar.SnackbarManager
import com.bonnjalal.wikiindaba.common.snackbar.SnackbarMessage
import com.bonnjalal.wikiindaba.data.online.online_entity.AttendanceOnlineEntity
import com.bonnjalal.wikiindaba.presentation.state.DataState
import com.bonnjalal.wikiindaba.presentation.ui.MainStateEvent
import com.bonnjalal.wikiindaba.presentation.ui.MainViewModel
import com.slaviboy.composeunits.dh
import com.slaviboy.composeunits.dw
import com.slaviboy.composeunits.sh
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanCustomCode
import io.github.g00fy2.quickie.ScanQRCode
import io.github.g00fy2.quickie.config.BarcodeFormat
import io.github.g00fy2.quickie.config.ScannerConfig


@Composable
fun ScanQrScreen (openAndPopUp: (String, String) -> Unit,popup: () -> Unit, vm:MainViewModel = hiltViewModel()) {

//    val attendanceState by vm.dataStateAttendance.collectAsStateWithLifecycle()
    val showAttendance by remember { vm.showAttendance }

    val scanQrCodeLauncher = rememberLauncherForActivityResult(ScanCustomCode()) { result ->
        // handle QRResult
        when (result) {
            is QRResult.QRSuccess -> {
                val value = result.content.rawValue
                // decoding with default UTF-8 charset when rawValue is null will not result in meaningful output, demo purpose
                    ?: result.content.rawBytes?.let { String(it) }.orEmpty()

                /**
                 * needs more logic to check if the name is in the attendees list
                 */
                val isInAttendees = vm.checkAttendee(value)
                if (isInAttendees){
                    vm.setStateEvent(MainStateEvent.SaveAttendanceEvent(vm.program.value!!.id, AttendanceOnlineEntity(value)))
                    vm.setStateEvent(MainStateEvent.GetAttendanceEvent)
                }else {
                    SnackbarManager.showMessage(SnackbarMessage.StringSnackbar("Error: $value is not in Attendees List"))
                }
            }
            QRResult.QRUserCanceled -> SnackbarManager.showMessage(R.string.by_user_error)
            QRResult.QRMissingPermission -> SnackbarManager.showMessage(SnackbarMessage.StringSnackbar("Please grant the app Camera permission"))
            is QRResult.QRError -> {
                result.exception.message?.let { SnackbarManager.showMessage(SnackbarMessage.StringSnackbar(it))}
            }//"${result.exception.javaClass.simpleName}: ${result.exception.localizedMessage}"
        }
//            .let { SnackbarManager.showMessage(SnackbarMessage.StringSnackbar(it)) }
    }



    val manualAddState by vm.manualAttendanceState
    Column (modifier = Modifier
        .background(color = Color(0xFFFFFFFF))
        .fillMaxSize()) {


        Row (Modifier.padding(top = 0.02.dh)){
            Spacer(modifier = Modifier.fillMaxWidth(0.1f))
            Text(
                modifier = Modifier.fillMaxWidth(0.8f),
                text = vm.program.value?.title ?: "Title",
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
//        val context = LocalContext.current
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
//                       vm.scanQrCode(context)
//                       vm.checkModuleAvailability(context = context)
                scanQrCodeLauncher.launch(
                    ScannerConfig.build {
                        setBarcodeFormats(listOf(BarcodeFormat.FORMAT_QR_CODE)) // set interested barcode formats
//                        setOverlayStringRes(R.string.scan_barcode) // string resource used for the scanner overlay
                        setOverlayDrawableRes(R.drawable.logo_wikiindaba) // drawable resource used for the scanner overlay
                        setHapticSuccessFeedback(false) // enable (default) or disable haptic feedback when a barcode was detected
                        setShowTorchToggle(true) // show or hide (default) torch/flashlight toggle button
                        setShowCloseButton(true) // show or hide (default) close button
//                        setHorizontalFrameRatio(2.2f) // set the horizontal overlay ratio (default is 1 / square frame)
//                        setUseFrontCamera(true) // use the front camera
                    }
                )
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

        var addFeildVisible by remember { mutableStateOf(false) }
        AnimatedVisibility(
            modifier = Modifier
//                    .offset(x = 0.07.dw, y = 0.2.dh)
                .height(0.05.dh)
                .fillMaxWidth(0.8f)
                .align(Alignment.CenterHorizontally)
            ,
            visible = !addFeildVisible,
            enter = fadeIn(
                // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
                initialAlpha = 0.4f
            ),
            exit = fadeOut(
                // Overwrites the default animation with tween
                animationSpec = tween(durationMillis = 250)
            )
        ) {
            // Content that needs to appear/disappear goes here:
            OutlinedButton(
            border = BorderStroke(color = Color(0xFF531B1C), width = 1.3.dp),
            shape = RoundedCornerShape(size = 0.01.dh),
            onClick = {
                addFeildVisible = true
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
        }
        AnimatedVisibility(
            modifier = Modifier
                .height(0.05.dh)
                .fillMaxWidth(0.8f)
                .align(Alignment.CenterHorizontally),
            visible = addFeildVisible,
            enter = fadeIn(
                // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
                initialAlpha = 0.4f
            ),
            exit = fadeOut(
                // Overwrites the default animation with tween
                animationSpec = tween(durationMillis = 250)
            )
        ) {
            // Content that needs to appear/disappear goes here:
            AddAttendanceField(value = manualAddState, onNewValue = vm::onManualAttendanceStateChange,
                addClicked = {
                    vm.setStateEvent(MainStateEvent.SaveAttendanceEvent(vm.program.value!!.id, AttendanceOnlineEntity(manualAddState)))
                    vm.setStateEvent(MainStateEvent.GetAttendanceEvent)
                    addFeildVisible = false
                }
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

        if (showAttendance){
            LazyColumn (modifier = Modifier
                .fillMaxWidth(0.8f)
                .align(Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.spacedBy((-8).dp)){
                // Add 5 items
                items((vm.dataStateAttendance.value as DataState.Success).data.attendanceList) { attendance ->

                    Row(Modifier.padding(0.dp)){
                        Text(
                            modifier = Modifier
                                .padding(end = 0.016.dw)
                                .fillMaxWidth(0.9f)
                                .align(Alignment.CenterVertically),
                            text = attendance,
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
                            onClick = {
                                vm.setStateEvent(MainStateEvent.DeleteAttendanceEvent(vm.program.value!!.id, AttendanceOnlineEntity(attendance)))
                                vm.setStateEvent(MainStateEvent.GetAttendanceEvent)
                            }) {
                            Icon(imageVector = ImageVector.vectorResource(R.drawable.delete_icon),
                                contentDescription = "delete icon",
                                tint = Color(0xFFA1A1A1))
                        }
                    }
                }
            }

        }else {
            Column (modifier = Modifier.align(Alignment.CenterHorizontally)){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color(0xFFF5EEDF))
                Text(modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Loading attendance ...", color = Color(0xFF531B1C),
                    fontSize = 0.013.sh)
            }
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAttendanceField(value: String,  onNewValue: (String) -> Unit,addClicked: () -> Unit, modifier: Modifier = Modifier) {

    CustomTextField(modifier = modifier
//                .padding(top = 60.dp)
//                .height(52.dp),
        ,
        singleLine = true,
        trailingIcon = {
            IconButton(onClick = {
                addClicked()
            }) {
                Icon(modifier = Modifier.background(Color(0xFF531B1C), shape = RoundedCornerShape(8.dp)),
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add icon")
            }

        },
        placeholder = { Text(
            text = "Enter attendee name ...",
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
