package com.earth.testomania

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.earth.testomania.presentation.NavGraphs
import com.earth.testomania.ui.theme.TestomaniaTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import com.ramcosta.composedestinations.spec.NavHostEngine
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestomaniaTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController: NavHostController = rememberNavController()

    Scaffold(
        topBar = {
            TopBar()
        },
    ) { innerPadding: PaddingValues ->
        ExampleNavigation(
            innerPadding = innerPadding,
            navHostController = navController
        )
    }
}

@Composable
fun ExampleNavigation(
    innerPadding: PaddingValues,
    navHostController: NavHostController,
) {

    val navHostEngine: NavHostEngine = rememberNavHostEngine()

    DestinationsNavHost(
        modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
        navGraph = NavGraphs.root,
        engine = navHostEngine,
        navController = navHostController,
    )
}