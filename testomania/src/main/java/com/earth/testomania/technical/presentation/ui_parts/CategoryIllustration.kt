package com.earth.testomania.technical.presentation.ui_parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.earth.testomania.technical.domain.model.TechCategory

@Composable
fun CategoryIllustration(modifier: Modifier, category: String) {
    TechCategory.findIllustrationFrom(category).apply {
        Image(
            modifier = modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = illustration),
            contentDescription = strValue
        )
    }
}
