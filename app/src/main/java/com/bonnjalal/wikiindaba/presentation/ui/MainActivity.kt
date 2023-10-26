package com.bonnjalal.wikiindaba.presentation.ui

import android.app.Activity
import android.graphics.Color.TRANSPARENT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import com.bonnjalal.wikiindaba.presentation.ui.screen.IndabaScreen
import com.bonnjalal.wikiindaba.presentation.ui.theme.WikiIndabaTheme
import com.slaviboy.composeunits.initSize
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSize()
//        enableEdgeToEdge(
//            statusBarStyle = SystemBarStyle.auto(TRANSPARENT, TRANSPARENT),
//            navigationBarStyle = SystemBarStyle.auto(TRANSPARENT, TRANSPARENT)
//        )
        setContent {
            WikiIndabaTheme(darkTheme = false) {
                val view = LocalView.current
                if (!view.isInEditMode) {
                    SideEffect {
                        val window = (view.context as Activity).window
                        window?.statusBarColor = Color(0xFFA39274).toArgb() // surface becomes the the status bar color

//                        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
//                            !darkTheme // not darkTheme makes the status bar icons visible
                    }
                }
                // A surface container using the 'background' color from the theme

               IndabaScreen()
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    WikiIndabaTheme {
//       Test(context = Activity(),name = "Zakaria", number = 2)
//    }
//}