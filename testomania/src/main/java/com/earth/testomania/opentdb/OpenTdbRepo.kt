package com.earth.testomania.opentdb

import com.earth.testomania.opentdb.models.OpenTdbQuizDto
import retrofit2.Response
import javax.inject.Inject

class OpenTdbRepo @Inject constructor(private val api: OpenTdbQuizApi) {

    suspend fun getQuiz(category: OpenTdbCategory, questionCount: Int): Response<OpenTdbQuizDto> {
        return api.getQuizList(
            category = category.id, questionCount = questionCount
        )
    }

    fun allCategories(): List<OpenTdbCategory> {
        return OpenTdbCategory.values().toList()
    }
}

enum class OpenTdbCategory(val id: Int) {
    GENERAL(9),
    BOOKS(10),
    FILM(11),
    MUSIC(12),
    MUSICALS_AND_THEATRES(13),
    TELEVISION(14),
    VIDEO_GAMES(15),
    BOARD_GAMES(16),
    NATURE(17),
    COMPUTERS(18),
    MATHEMATICS(19),
    MYTHOLOGY(20),
    SPORTS(21),
    GEOGRAPHY(22),
    HISTORY(23),
    POLITICS(24),
    ART(25),
    CELEBRITIES(26),
    ANIMALS(27),
    VEHICLES(28),
    COMICS(29),
    GADGETS(30),
    ANIME_MANGA(31),
}