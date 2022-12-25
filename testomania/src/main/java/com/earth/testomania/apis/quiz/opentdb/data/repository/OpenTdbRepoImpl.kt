package com.earth.testomania.apis.quiz.opentdb.data.repository

import android.text.Html
import com.earth.testomania.apis.quiz.opentdb.data.source.OpenTdbQuizApi
import com.earth.testomania.apis.quiz.opentdb.data.source.remote.dto.ResultDto
import com.earth.testomania.apis.quiz.opentdb.domain.models.OpenTDBApiCategory
import com.earth.testomania.apis.quiz.opentdb.domain.repository.OpenTdbRepo
import com.earth.testomania.common.data.DataState
import com.earth.testomania.common.model.Answer
import com.earth.testomania.common.model.Quiz
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OpenTdbRepoImpl @Inject constructor(private val api: OpenTdbQuizApi) : OpenTdbRepo {

    override suspend fun getQuiz(
        category: OpenTDBApiCategory, questionCount: Int
    ): Flow<DataState<List<Quiz>>> {
        return flow {
            val result = api.getQuizList(
                category = category.id, questionCount = questionCount
            )

            val quizList = result.body()?.results?.mapIndexed { i: Int, resultDto: ResultDto ->
                Quiz(id = i,
                    point = 1.0,
                    question = parseText(resultDto.question),
                    category = resultDto.category.replace("Entertainment:", "")
                        .replace("Science:", "").trim(),
                    answers = buildList {
                        resultDto.incorrect_answers.forEachIndexed { index, answer ->
                            add(Answer(index.toString(), parseText(answer), null, false))
                        }
                        add(
                            Answer(
                                tag = count().plus(1).toString(),
                                text = parseText(resultDto.correct_answer),
                                image = null,
                                isCorrect = true
                            )
                        )
                    }.shuffled().mapIndexed { index, answer ->
                        answer.copy(tag = (index + 1).toString())
                    })
            }

            emit(DataState.Success(payload = quizList))
        }
    }

    private fun parseText(text: String) = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY).toString()
}