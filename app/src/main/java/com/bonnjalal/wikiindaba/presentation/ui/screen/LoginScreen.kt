package com.bonnjalal.wikiindaba.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.bonnjalal.wikiindaba.R
import com.bonnjalal.wikiindaba.presentation.ui.MainViewModel
import com.bonnjalal.wikiindaba.presentation.ui.theme.WikiIndabaTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(vm: MainViewModel = hiltViewModel()){

    val uiState by vm.loginUiState


    ConstraintLayout (modifier = Modifier.fillMaxSize()){
        // Create references for the composables to constrain
        val (img1,img2,col1, img) = createRefs()

        Image(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(img1) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            , imageVector = ImageVector.vectorResource(id = R.drawable.top_wave),
            contentDescription = "wiki logo2", contentScale = ContentScale.FillWidth)

        Row (modifier = Modifier
            .constrainAs(img2) {
                top.linkTo(img1.bottom, margin = (-25).dp)
                start.linkTo(parent.start)
            }){
            
            Spacer(modifier = Modifier.fillMaxSize(0.1f))
            Image(modifier = Modifier
                .width(160.dp)
                .height(60.dp)
//            .size(width = 160.dp, height = 60.dp)
                , imageVector = ImageVector.vectorResource(id = R.drawable.logo_wikiindaba),
                contentDescription = "indaba logo", contentScale = ContentScale.FillBounds)

        }
        
        Column (modifier = Modifier
            .fillMaxWidth(0.8f)
            .constrainAs(col1) {
                height = Dimension.fillToConstraints
//                width = Dimension.matchParent
                top.linkTo(img2.bottom, 60.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(img.top, margin = 8.dp)
            }) {

//            Text(text = "Sign in", fontSize = 30.sp, fontWeight = FontWeight.SemiBold)
            Text(
                text = "Sign in",
                style = TextStyle(
                    fontSize = 26.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF1C2813),
                )
            )
            Text(
                text = "Please fill the credentials",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Color.Gray,
                )
            )

            Spacer(Modifier.height(60.dp))
//            var username by remember { mutableStateOf("") }
//            var username by rememberSaveable { mutableStateOf("") }
            EmailField(uiState.email, vm::onEmailChange)

            Spacer(Modifier.height(20.dp))
//            var password by remember { mutableStateOf("") }
//            var password by rememberSaveable { mutableStateOf("") }
            PasswordField(uiState.password, vm::onPasswordChange)

            Spacer(modifier = Modifier.height(50.dp))
            Box (modifier = Modifier
                .height(52.dp)
                .fillMaxWidth()
//                .padding(top = 40.dp)
                .background(
                    color = Color(0xFF531B1C),
                    shape = RoundedCornerShape(size = 10.dp)
                )
                .clickable {
                           vm.onSignInClick()
                },
                contentAlignment = Alignment.Center) {

                Text(
                    text = "Sign in",
                    style = TextStyle(
                        fontSize = 17.sp,
//                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFFFFF),
                    )
                )
            }

            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = "If you encounter difficulties in registering, please kindly contact one of the WikiIndaba 2023 organizing members to connect you with the responsible team for the application.",
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 17.sp,
//                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFFA1A1A1),
                    textAlign = TextAlign.Justify,
                )
            )

        }

        Image(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(img) {
                bottom.linkTo(parent.bottom, margin = 0.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            , imageVector = ImageVector.vectorResource(id = R.drawable.bottom_wave),
            contentDescription = "wiki logo", contentScale = ContentScale.FillWidth)

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(value: String,  onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {

    TextField(modifier = Modifier
//                .padding(top = 60.dp)
        .fillMaxWidth(),
//                .height(52.dp),
        singleLine = true,
        leadingIcon = {
            Icon(imageVector = ImageVector.vectorResource(R.drawable.user_outlined),
                contentDescription = "user icon" )
        },
        placeholder = { Text(text = "Email") },
        colors = TextFieldDefaults.textFieldColors(containerColor = Color(0xFFF5EEDF),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(value: String,  onNewValue: (String) -> Unit, modifier: Modifier = Modifier){
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val image = if (passwordVisible)
        ImageVector.vectorResource(R.drawable.eye)
    else ImageVector.vectorResource(R.drawable.eye_off)
    TextField(
        modifier = Modifier
//                .padding(top = 20.dp)
            .fillMaxWidth(),
//                .height(52.dp),
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.user_key),
                contentDescription = "user icon"
            )
        },
        placeholder = { Text(text = "Password") },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFF5EEDF),
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(size = 10.dp),
        value = value,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {

            // Please provide localized description for accessibility services
            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, description)
            }
        },
        textStyle = TextStyle (
            fontSize = 14.sp,
//                        fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight(400),
            color = Color(0xFFA39274),
        ) , onValueChange = {
            onNewValue(it)
        })
}
@Preview
@Composable
fun preveiwLogin(){
    Surface (modifier = Modifier
        .background(color = Color.White)
        .fillMaxSize()){
        LoginScreen()
    }
}