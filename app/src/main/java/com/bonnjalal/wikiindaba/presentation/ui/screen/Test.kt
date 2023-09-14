package com.bonnjalal.wikiindaba.presentation.ui.screen

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.bonnjalal.wikiindaba.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Test(context: Activity, name:String, number: Int){

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)){

        val scroll = rememberScrollState()

        Column (modifier = Modifier
            .wrapContentSize()
            .scrollable(state = scroll, orientation = Orientation.Vertical)) {

            Image(modifier = Modifier.padding(12.dp, 5.dp), painter = painterResource(id = R.drawable.scholarship_logo),
                contentDescription = "wiki logo", contentScale = ContentScale.Fit)



            Column (modifier = Modifier
                .size(width = 100.dp, height = 300.dp)
                .background(color = Color.Cyan) ) {
                Text(
                    text = "Hello $name! _ $number",
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .background(color = Color.Blue)
                )

                Text(text = "hello jalal Hello zakaria")

            }

            Row {
                Text(text = "hello 2222")
                Text(text = "Hoello 33333")
            }

            Box(modifier = Modifier
                .background(color = Color.Blue)
                .clickable {
                    Toast
                        .makeText(context, "box clicked", Toast.LENGTH_SHORT)
                        .show()
                }){
                Text(text = "Click me")

            }

            Button(onClick = {
                Toast
                    .makeText(context, "box clicked", Toast.LENGTH_SHORT)
                    .show()
            }) {
                Text(text = "Click here")
            }

            ElevatedButton(onClick = { /*TODO*/ }) {
                Text(text = "Click here")
            }

            FilledIconButton(onClick = { /*TODO*/ }) {
                Text(text = "Click here")
            }

            OutlinedButton(onClick = { /*TODO*/ }) {
                Text(text = "Click here")
            }

            TextField(value = "text ...", onValueChange = {

            })




        }

        Box () {
            Image(modifier = Modifier
                .padding(12.dp, 5.dp)
                .align(Alignment.BottomEnd), painter = painterResource(id = R.drawable.scholarship_logo),
                contentDescription = "wiki logo", contentScale = ContentScale.Fit)
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Test2(context: Activity){

    val scroll = rememberScrollState()
    ConstraintLayout {
        // Create references for the composables to constrain
        val (column22, img) = createRefs()

        Column(modifier = Modifier.fillMaxWidth()
            .verticalScroll(state = scroll)
            .constrainAs(column22){
            top.linkTo(parent.top, margin = 8.dp)
            bottom.linkTo(img.top)
        }) {
            Image(painter = painterResource(id = R.drawable.scholarship_logo),
                contentDescription = "wiki logo", contentScale = ContentScale.Fit)



            Column (modifier = Modifier
                .size(width = 100.dp, height = 300.dp)
                .background(color = Color.Cyan) ) {
                Text(
                    text = "Hello World",
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .background(color = Color.Blue)
                )

                Text(text = "hello jalal Hello zakaria")

            }

            Row {
                Text(text = "hello 2222")
                Text(text = "Hoello 33333")
            }

            Box(modifier = Modifier
                .background(color = Color.Blue)
                .clickable {
                    Toast
                        .makeText(context, "box clicked", Toast.LENGTH_SHORT)
                        .show()
                }){
                Text(text = "Click me")

            }

            Column (modifier = Modifier
                .size(width = 100.dp, height = 300.dp)
                .background(color = Color.Cyan) ) {
                Text(
                    text = "Hello World",
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .background(color = Color.Blue)
                )

                Text(text = "hello jalal Hello zakaria")

            }

            Row {
                Text(text = "hello 2222")
                Text(text = "Hoello 33333")
            }

            Box(modifier = Modifier
                .background(color = Color.Blue)
                .clickable {
                    Toast
                        .makeText(context, "box clicked", Toast.LENGTH_SHORT)
                        .show()
                }){
                Text(text = "Click me")

            }

            Button(onClick = {
                Toast
                    .makeText(context, "box clicked", Toast.LENGTH_SHORT)
                    .show()
            }) {
                Text(text = "Click here")
            }

            ElevatedButton(onClick = { /*TODO*/ }) {
                Text(text = "Click here")
            }

            FilledIconButton(onClick = { /*TODO*/ }) {
                Text(text = "Click here")
            }

            OutlinedButton(onClick = { /*TODO*/ }) {
                Text(text = "Click here")
            }

            TextField(value = "text ...", onValueChange = {

            })

            // Assign reference "text" to the Text composable
            // and constrain it to the bottom of the Button composable
            Text(
                "Text",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }


        Image(modifier = Modifier
            .constrainAs(img){
                             bottom.linkTo(parent.bottom, margin = 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            , painter = painterResource(id = R.drawable.scholarship_logo),
            contentDescription = "wiki logo", contentScale = ContentScale.Fit)

    }
}
