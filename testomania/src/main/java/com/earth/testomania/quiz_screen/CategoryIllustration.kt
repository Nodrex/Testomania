package com.earth.testomania.quiz_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.earth.testomania.apis.quizapi.domain.model.TechCategory

@Composable
fun CategoryIllustration(modifier: Modifier = Modifier, category: String) {
    TechCategory.findSpecific(category).apply {
        Image(
            modifier = modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = illustration),
            contentDescription = this.category
        )
    }
}
