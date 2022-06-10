package com.earth.testomania.presentation.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.earth.testomania.R
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kiwi.orbit.compose.ui.controls.LinearProgressIndicator
import kiwi.orbit.compose.ui.controls.Text

@Preview(showSystemUi = true)
@Destination(
    route = "home/result",
    deepLinks = [
        DeepLink(uriPattern = "testomania://home/result")
    ]
)
@Composable
fun ResultScreen() {

    val viewModel: ResultScreenViewModel = hiltViewModel()

    val illustrationRes =
        if (viewModel.isTestDone) R.drawable.il_orbit_success else R.drawable.il_orbit_error

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Spacer(modifier = Modifier.size(30.dp))
        Text(text = "hi there")
        MainResultItem(
            "test name",
//            mainColor = Color.Magenta,
            progress = 0.65f
        )
        Image(
            painter = painterResource(id = illustrationRes),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        )
    }

}

@Preview
@Composable
fun MainResultItem(
    testName: String = "test",
    mainColor: Color = Color.Blue,
    progress: Float = 0.0f
) {

    val lighterColor = mainColor.copy(alpha = 0.1f)
    val lightColor = mainColor.copy(alpha = 0.2f)

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(corner = CornerSize(12.dp)))
            .fillMaxWidth()
            .background(lighterColor)
            .padding(8.dp, 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(lightColor)
                .padding(10.dp),
            painter = painterResource(id = R.drawable.ic_driver_license),
            contentDescription = "Test icon",
            colorFilter = ColorFilter.tint(mainColor)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp, 0.dp)
        ) {
            Text(
                text = testName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                color = mainColor,
                fontSize = 12.sp,
                text = "$progress% correct"
            )
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 3.dp)
                    .height(8.dp),
                indicatorColor = mainColor,
                trackColor = lightColor
            )
        }
        Image(
            modifier = Modifier
                .size(40.dp)
                .padding(10.dp),
            painter = painterResource(id = R.drawable.ic_forward),
            contentDescription = "no action",
            colorFilter = ColorFilter.tint(Color.Gray)
        )
    }


}