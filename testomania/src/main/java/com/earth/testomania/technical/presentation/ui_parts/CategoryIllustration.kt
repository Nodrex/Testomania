package com.earth.testomania.technical.presentation.ui_parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.earth.testomania.R

@Composable
fun CategoryIllustration(modifier: Modifier, category: String) {
    Image(modifier = modifier.fillMaxWidth(), contentScale = ContentScale.Crop,
        painter = painterResource(id = findImageCategory(category)), contentDescription = "")
}

private fun findImageCategory(category: String): Int {
    category.contains("asdf")
    return when {
        category.contains("Linux", ignoreCase = true) -> R.drawable.il_os
        category.contains("JS", ignoreCase = true) -> R.drawable.il_js
        category.contains("PHP", ignoreCase = true) -> R.drawable.il_php
        category.contains("Networking", ignoreCase = true) -> R.drawable.il_networking
        category.contains("Cloud", ignoreCase = true) -> R.drawable.il_cloud
        category.contains("Docker", ignoreCase = true) -> R.drawable.il_docker
        category.contains("Kubernetes", ignoreCase = true) -> R.drawable.il_kubernetes
        category.contains("html", ignoreCase = true) -> R.drawable.il_html
        category.contains("sql", ignoreCase = true) -> R.drawable.il_sql
        category.contains("wordpress", ignoreCase = true) -> R.drawable.il_wordpress
        else -> R.drawable.il_unknown
    }
}