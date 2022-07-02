package com.earth.testomania

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.earth.testomania.presentation.CreateAboutBottomSheet
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import com.ramcosta.composedestinations.spec.NavHostEngine
import dagger.hilt.android.AndroidEntryPoint
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.foundation.darkColors
import kiwi.orbit.compose.ui.foundation.lightColors

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val systemUiController = rememberSystemUiController()
            val isSystemInDarkTheme = isSystemInDarkTheme()

            SideEffect {
                systemUiController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = !isSystemInDarkTheme,
                )
            }

            OrbitTheme(colors = if (isSystemInDarkTheme) darkColors() else lightColors()) {
                Testomania()
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Testomania() {
    val navController: NavHostController = rememberNavController()

    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val state = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = state,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            CreateAboutBottomSheet()
        }) {

        TestomaniaNavigation(
            navHostController = navController
        )

        /*Button(onClick = {
            coroutineScope.launch {
                if (sheetState.isCollapsed) sheetState.expand()
                else sheetState.collapse()
            }
        }) {

        }*/
    }
}

@Composable
fun TestomaniaNavigation(
    navHostController: NavHostController,
) {

    val navHostEngine: NavHostEngine = rememberNavHostEngine()

    DestinationsNavHost(
        navGraph = NavGraphs.root,
        engine = navHostEngine,
        navController = navHostController,
    )
}