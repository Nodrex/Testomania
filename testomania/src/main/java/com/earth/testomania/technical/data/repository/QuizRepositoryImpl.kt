package com.earth.testomania.technical.data.repository

import com.earth.testomania.common.DataState
import com.earth.testomania.common.ErrorMetaData
import com.earth.testomania.common.SuccessMetaData
import com.earth.testomania.common.model.Answer
import com.earth.testomania.common.model.Quiz
import com.earth.testomania.technical.data.source.remote.QuizApi
import com.earth.testomania.technical.data.source.remote.dto.TagDTO
import com.earth.testomania.technical.data.source.remote.dto.TechQuizDTO
import com.earth.testomania.technical.domain.model.AnswerKey
import com.earth.testomania.technical.domain.model.QuizCategory
import com.earth.testomania.technical.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val quizApi: QuizApi
) : QuizRepository {

    override suspend fun getQuizList(params: QuizCategory): Flow<DataState<List<Quiz>>> =
        flow {
            quizApi.getQuizList(
                category = params.category,
                tags = params.tag
            ).apply {
                if (isSuccessful) {
                    try {
                        val quizList = mutableListOf<Quiz?>()
                        body()?.forEach {
                            quizList += convertTechQuizDTOtoDataObject(it)
                        }
                        emit(
                            DataState.Success(
                                SuccessMetaData(),
                                payload = quizList.filterNotNull()
                            )
                        )
                    } catch (e: Exception) {
                        emit(DataState.Error(ErrorMetaData(e)))
                    }
                } else emit(DataState.Error(ErrorMetaData(null)))
            }
        }


    private fun convertTechQuizDTOtoDataObject(techQuizDTO: TechQuizDTO): Quiz? {
        val possibleAnswersList = generatePossibleAnswersList(techQuizDTO)

        val correctAnswersList =
            generateCorrectAnswersList(techQuizDTO, possibleAnswersList?.size ?: 0) ?: return null

        val possibleAnswers = createPossibleAnswerMap(possibleAnswersList ?: emptyList())
        val correctAnswers = createCorrectAnswerMap(correctAnswersList)

        val answers = mutableListOf<Answer>()
        possibleAnswers.forEach {
            answers +=
                Answer(
                    tag = it.key.first(),
                    text = it.value,
                    image = "",
                    isCorrect = correctAnswers[it.key] ?: false
                )
        }

        return with(techQuizDTO) {
            Quiz(
                id = id,
                point = 1.0,
                question = question ?: "",
                category = addTagsToCategory(category, tags),
                explanation = explanation ?: "",
                answers = answers
            )
        }
    }

    private fun addTagsToCategory(category: String?, tags: List<TagDTO>?): String {
        val joinedTags = tags?.mapNotNull {
            it.takeIf {
                !(it.name.equals(
                    category,
                    ignoreCase = true
                ))
            }?.name
        }?.joinToString(separator = "/")
        return when {
            category.isNullOrEmpty() -> joinedTags ?: ""
            joinedTags.isNullOrEmpty() -> category
            else -> "$category/$joinedTags"
        }
    }

    private fun generatePossibleAnswersList(techQuizDTO: TechQuizDTO) = techQuizDTO.answers?.let {
        listOfNotNull(
            it.answer_a,
            it.answer_b,
            it.answer_c,
            it.answer_d,
            it.answer_e,
            it.answer_f,
        )
    }

    private fun generateCorrectAnswersList(techQuizDTO: TechQuizDTO, trimSize: Int) =
        techQuizDTO.correct_answers?.let {
            listOfNotNull(
                it.answer_a_correct?.toBoolean(),
                it.answer_b_correct?.toBoolean(),
                it.answer_c_correct?.toBoolean(),
                it.answer_d_correct?.toBoolean(),
                it.answer_e_correct?.toBoolean(),
                it.answer_f_correct?.toBoolean(),
            ).subList(0, trimSize)
        }

    private fun createPossibleAnswerMap(possibleAnswerList: List<String>): Map<String, String> {
        val map = mutableMapOf<String, String>()
        possibleAnswerList.forEachIndexed { index, answer ->
            map[AnswerKey.list[index]] = answer
        }
        return map
    }

    private fun createCorrectAnswerMap(correctAnswersList: List<Boolean>): Map<String, Boolean> {
        val map = mutableMapOf<String, Boolean>()
        correctAnswersList.forEachIndexed { index, answer ->
            map[AnswerKey.list[index]] = answer
        }
        return map
    }

}