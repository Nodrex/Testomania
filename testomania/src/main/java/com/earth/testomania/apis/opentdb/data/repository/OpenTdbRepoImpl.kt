package com.earth.testomania.apis.opentdb.data.repository

import com.earth.testomania.apis.opentdb.data.source.OpenTdbQuizApi
import com.earth.testomania.apis.opentdb.data.source.remote.dto.ResultDto
import com.earth.testomania.apis.opentdb.domain.models.OpenTdbCategory
import com.earth.testomania.apis.opentdb.domain.repository.OpenTdbRepo
import com.earth.testomania.common.DataState
import com.earth.testomania.common.model.Answer
import com.earth.testomania.common.model.Quiz
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OpenTdbRepoImpl @Inject constructor(private val api: OpenTdbQuizApi) : OpenTdbRepo {

    override suspend fun getQuiz(
        category: OpenTdbCategory, questionCount: Int
    ): Flow<DataState<List<Quiz>>> {
        return flow {
            val result = api.getQuizList(
                category = category.id, questionCount = questionCount
            )

            val quizList = result.body()?.results?.mapIndexed { i: Int, resultDto: ResultDto ->
                Quiz(id = i,
                    point = 1.0,
                    question = resultDto.question,
                    category = resultDto.category,
                    answers = buildList {
                        resultDto.incorrect_answers.forEachIndexed { index, answer ->
                            add(Answer(index.toString(), answer, null, false))
                        }
                        add(
                            Answer(
                                tag = count().plus(1).toString(),
                                text = resultDto.correct_answer,
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
}