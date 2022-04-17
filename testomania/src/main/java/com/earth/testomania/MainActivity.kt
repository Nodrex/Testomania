package com.earth.testomania

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.earth.testomania.driving_licence.presentation.DrivingLicenceViewModel
import com.earth.testomania.presentation.NavGraphs
import com.earth.testomania.presentation.TopBar
import com.earth.testomania.ui.theme.TestomaniaTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import com.ramcosta.composedestinations.spec.NavHostEngine
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<DrivingLicenceViewModel>()
        viewModel.startNewTest()
        setContent {
            TestomaniaTheme {
                Testomania()
            }
        }
    }
}

@Composable
fun Testomania() {
    val navController: NavHostController = rememberNavController()

    Scaffold(
        topBar = {
            TopBar()
        },
    ) { innerPadding: PaddingValues ->
        TestomaniaNavigation(
            innerPadding = innerPadding,
            navHostController = navController
        )
    }
}

@Composable
fun TestomaniaNavigation(
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