package com.bonnjalal.wikiindaba.presentation.ui.screen

import android.annotation.SuppressLint
import android.content.res.Resources
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bonnjalal.wikiindaba.common.snackbar.SnackbarManager
import com.bonnjalal.wikiindaba.presentation.state.MakeItSoAppState
import kotlinx.coroutines.CoroutineScope

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IndabaScreen() {
//    val navController = rememberNavController()

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

        val appState = rememberAppState()



//        val snackbarHostState = remember { SnackbarHostState() }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            snackbarHost = {
//                SnackbarHost(hostState = snackbarHostState)
                SnackbarHost(
                    hostState = appState.snackbarHostState,
                    modifier = Modifier.padding(8.dp),
                    snackbar = { snackbarData ->
                        Snackbar(snackbarData)
                    }
                )
            },

        ) {
            // Screen content
            NavHost(navController = appState.navController, startDestination = "login") {
//                composable("login") { LoginScreen() }
//                composable("login") { ProgramScreen() }
                composable("login") { ScanQrScreen() }
            }
        }

    }

}

@JvmOverloads
@Composable
fun rememberAppState(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
    remember(snackbarHostState, navController, snackbarManager, resources, coroutineScope) {
        MakeItSoAppState(snackbarHostState, navController, snackbarManager, resources, coroutineScope)
    }

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}
@Preview
@Composable
fun previewIndabaScreen(){
    IndabaScreen()
}