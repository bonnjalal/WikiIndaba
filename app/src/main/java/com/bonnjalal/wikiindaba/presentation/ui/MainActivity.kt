package com.bonnjalal.wikiindaba.presentation.ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bonnjalal.wikiindaba.presentation.ui.screen.IndabaScreen
import com.bonnjalal.wikiindaba.presentation.ui.screen.LoginScreen
import com.bonnjalal.wikiindaba.presentation.ui.screen.Test
import com.bonnjalal.wikiindaba.presentation.ui.screen.Test2
import com.bonnjalal.wikiindaba.presentation.ui.theme.WikiIndabaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WikiIndabaTheme {

                // A surface container using the 'background' color from the theme
               IndabaScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WikiIndabaTheme {
       Test(context = Activity(),name = "Zakaria", number = 2)
    }
}