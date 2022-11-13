package com.earth.testomania.home_screen.domain

import com.earth.testomania.opentdb.OpenTdbRepo
import javax.inject.Inject

class OpenTdbUseCase @Inject constructor(
    private val openTdbRepo: OpenTdbRepo
) {
    operator fun invoke() {
        // TODO
    }
}