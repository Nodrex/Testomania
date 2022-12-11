package com.earth.testomania.opentdb

import com.earth.testomania.common.DataState
import com.earth.testomania.common.model.Answer
import com.earth.testomania.common.model.Quiz
import com.earth.testomania.opentdb.models.ResultDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface OpenTdbRepo {
    suspend fun getQuiz(
        category: OpenTdbCategory,
        questionCount: Int
    ): Flow<DataState<List<Quiz>>>
}

class OpenTdbRepoImpl @Inject constructor(private val api: OpenTdbQuizApi) : OpenTdbRepo {

    override suspend fun getQuiz(
        category: OpenTdbCategory,
        questionCount: Int
    ): Flow<DataState<List<Quiz>>> {
        //TODO emit a result
        return flow {
            val result = api.getQuizList(
                category = category.id, questionCount = questionCount
            )

            val quizList = result.body()?.results?.mapIndexed { i: Int, resultDto: ResultDto ->
                Quiz(
                    id = i,
                    point = 1.0,
                    question = resultDto.question,
                    category = resultDto.category,
                    answers = resultDto.incorrect_answers.mapIndexed() { index: Int, answer: String ->
                        Answer(index.toChar(), answer, null, answer == resultDto.correct_answer)
                    }
                )
            }

            emit(DataState.Success(payload = quizList))
        }
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