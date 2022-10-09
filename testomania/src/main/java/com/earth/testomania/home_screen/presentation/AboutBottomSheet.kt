@file:OptIn(ExperimentalMaterialApi::class)

package com.earth.testomania.home_screen.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.earth.testomania.R
import com.earth.testomania.common.Crashlytics
import com.earth.testomania.common.Developer
import com.earth.testomania.common.developers
import com.ramcosta.composedestinations.annotation.Destination
import kiwi.orbit.compose.ui.controls.ChoiceTile
import kiwi.orbit.compose.ui.controls.Icon
import kiwi.orbit.compose.ui.controls.Text
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

const val ABOUT_ROUT = "home/about"

@Destination(route = ABOUT_ROUT)
@Composable
fun AboutBottomSheet(
    modalBottomSheetState: ModalBottomSheetState,
    scope: CoroutineScope
) {
    val context = LocalContext.current
    val appGithubUrl = stringResource(id = R.string.app_github_url)
    val githubProblemText = stringResource(id = R.string.github_activity_problem)
    val linkedInProblemText = stringResource(id = R.string.linkedin_activity_problem)

    Text(
        modifier = Modifier
            .padding(all = 20.dp)
            .padding(top = 20.dp),
        fontSize = 16.sp,
        text = stringResource(id = R.string.about_app)
    )

    Row(modifier = Modifier
        .wrapContentSize()
        .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
        .clickable {
            startActivity(
                context,
                Intent(Intent.ACTION_VIEW, Uri.parse(appGithubUrl)),
                null
            )
        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_orbit_github),
            contentDescription = "",
        )
        Text(
            modifier = Modifier.padding(start = 10.dp),
            fontSize = 16.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = appGithubUrl
        )
    }

    Text(
        modifier = Modifier.padding(start = 12.dp, bottom = 8.dp, end = 10.dp),
        fontSize = 14.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        text = stringResource(id = R.string.creator_developers)
    )

    developers.forEach {
        AboutDeveloper(it, githubProblemText, linkedInProblemText)
    }

    Icon(
        modifier = Modifier
            .height(40.dp)
            .padding(bottom = 10.dp, start = 100.dp, end = 100.dp)
            .fillMaxWidth()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            {
                scope.launch {
                    modalBottomSheetState.hide()
                }
            },
        painter = painterResource(id = R.drawable.ic_orbit_chevron_down),
        contentDescription = "",
    )

}

@Composable
fun AboutDeveloper(developer: Developer, githubProblemText: String, linkedInProblemText: String) {

    val context = LocalContext.current

    ChoiceTile(
        modifier = Modifier.padding(
            start = 10.dp,
            end = 10.dp,
            bottom = 10.dp
        ),
        selected = false,
        showRadio = false,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = developer.fullName)

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {

                    Icon(
                        modifier = Modifier.clickable {

                            openBrowser(context, developer.githubProfileLink, githubProblemText)
                        },
                        painter = painterResource(id = R.drawable.ic_orbit_github),
                        contentDescription = "",
                    )


                    Icon(
                        modifier = Modifier.clickable {
                            openBrowser(context, developer.linkedinProfileLink, linkedInProblemText)
                        },
                        painter = painterResource(id = R.drawable.ic_orbit_linkedin),
                        contentDescription = "",
                    )
                }
            }
        },
        onSelect = {}
    )
}

private fun openBrowser(context: Context, url: String, toastText: String) {
    try {
        startActivity(
            context,
            Intent(Intent.ACTION_VIEW, Uri.parse(url)),
            null
        )
    } catch (e: Exception) {
        Crashlytics.recordException(e)
        Toast.makeText(context, toastText, Toast.LENGTH_LONG).show()
    }
}