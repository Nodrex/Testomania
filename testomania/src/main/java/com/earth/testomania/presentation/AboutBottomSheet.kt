package com.earth.testomania.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.earth.testomania.R
import com.earth.testomania.core.Developer
import kiwi.orbit.compose.ui.controls.ChoiceTile
import kiwi.orbit.compose.ui.controls.Icon


@Composable
fun CreateAboutBottomSheet() {

    val context = LocalContext.current
    val appGithubUrl = stringResource(id = R.string.app_github_url)

    Text(
        modifier = Modifier.padding(all = 20.dp),
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

    AboutDeveloper(Developer.GIORGI_SHALVASHVILI)
    AboutDeveloper(Developer.KARTLOS_DIAKONIDZE)
    AboutDeveloper(Developer.NODAR_TCHUMBADZE)
    AboutDeveloper(Developer.NIKA_MGALOBLISHVILI)
    AboutDeveloper(Developer.LIKA_GLONTI)
}

@Composable
fun AboutDeveloper(developer: Developer) {

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
                            openBrowser(context, developer.githubProfileLink)
                        },
                        painter = painterResource(id = R.drawable.ic_orbit_github),
                        contentDescription = "",
                    )
                    Icon(
                        modifier = Modifier.clickable {
                            openBrowser(context, developer.linkedinProfileLink)
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

private fun openBrowser(context: Context, url: String) {
    startActivity(
        context,
        Intent(Intent.ACTION_VIEW, Uri.parse(url)),
        null
    )
}