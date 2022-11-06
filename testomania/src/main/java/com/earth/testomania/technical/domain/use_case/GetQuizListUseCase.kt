package com.earth.testomania.technical.domain.use_case

import com.earth.testomania.common.*
import com.earth.testomania.common.model.QuizUIState
import com.earth.testomania.technical.data.source.remote.QUIZ_API_PATH
import com.earth.testomania.technical.data.source.remote.dto.TagDTO
import com.earth.testomania.technical.data.source.remote.dto.TechQuizDTO
import com.earth.testomania.technical.di.QUIZ_API_BASE_URL
import com.earth.testomania.technical.domain.model.AnswerKey
import com.earth.testomania.technical.domain.model.QuizCategory
import com.earth.testomania.technical.domain.model.TechQuiz
import com.earth.testomania.technical.domain.repository.QuizRepository
import kotlinx.coroutines.flow.*
import java.util.concurrent.CancellationException
import javax.inject.Inject

class GetQuizListUseCase @Inject constructor(
    private val quizRepository: QuizRepository
) {

    suspend operator fun invoke(params: QuizCategory): Flow<DataState<List<QuizUIState>>> =
        flow {
            try {
                emit(DataState.Loading(LoadingMetaData()))
                quizRepository.getQuizList(params).map {
                    when (it) {
                        is DataState.Success -> emit(success(it.payload))
                        is DataState.Error -> emit(error(it))
                        is DataState.Loading -> emit(loading(it))
                    }
                }.catch {
                    emit(DataState.Error(ErrorMetaData(it.cause as Exception?)))
                }.collect()
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                emit(DataState.Error(ErrorMetaData(e)))
            }
        }

    private fun convertTechQuizDTOtoDataObject(techQuizDTO: TechQuizDTO) = with(techQuizDTO) {
        val possibleAnswersList = generatePossibleAnswersList(techQuizDTO) ?: return@with null

        val correctAnswersList =
            generateCorrectAnswersList(techQuizDTO, possibleAnswersList.size) ?: return@with null

        return@with TechQuizItemWrapper(
            TechQuiz(
                id = id,
                question = question ?: "",
                category = addTagsToCategory(category, tags),
                explanation = explanation ?: "",
                hasMultiAnswer = multiple_correct_answers?.toBoolean() ?: false,
                possibleAnswers = createPossibleAnswerMap(possibleAnswersList),
                correctAnswers = createCorrectAnswerMap(correctAnswersList)
            )
        )
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

    private fun generatePossibleAnswersList(techQuizDTO: TechQuizDTO) = with(techQuizDTO) {
        return@with techQuizDTO.answers?.let {
            return@let listOfNotNull(
                it.answer_a,
                it.answer_b,
                it.answer_c,
                it.answer_d,
                it.answer_e,
                it.answer_f,
            )
        }
    }

    private fun generateCorrectAnswersList(techQuizDTO: TechQuizDTO, trimSize: Int) =
        with(techQuizDTO) {
            return@with techQuizDTO.correct_answers?.let {
                return@let listOfNotNull(
                    it.answer_a_correct?.toBoolean(),
                    it.answer_b_correct?.toBoolean(),
                    it.answer_c_correct?.toBoolean(),
                    it.answer_d_correct?.toBoolean(),
                    it.answer_e_correct?.toBoolean(),
                    it.answer_f_correct?.toBoolean(),
                ).subList(0, trimSize)
            }
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

    private fun success(list: List<TechQuizDTO>?) =
        DataState.Success(
            SuccessMetaData(),
            list?.filter {
                it.multiple_correct_answers == FALSE_STR && hasCorrectAnswer(it)
                //TODO this filter is temporarily to disable multi answer quiz,
                // but we wel remove this constraint for future on next releases of App
            }?.mapNotNull { techQuiz ->
                convertTechQuizDTOtoDataObject(techQuiz)
            })

    private fun hasCorrectAnswer(techQuizDTO: TechQuizDTO): Boolean {
        techQuizDTO.correct_answers?.let {
            val hasCorrectAnswer = it.answer_a_correct.toBoolean() ||
                    it.answer_b_correct.toBoolean() ||
                    it.answer_c_correct.toBoolean() ||
                    it.answer_d_correct.toBoolean() ||
                    it.answer_e_correct.toBoolean() ||
                    it.answer_f_correct.toBoolean()
            if (!hasCorrectAnswer) {
                log(
                    "Problematic Quiz (Without correct answer) from: $QUIZ_API_BASE_URL$QUIZ_API_PATH" +
                            "\n\t$techQuizDTO" +
                            "\n[Please report to API creators]"
                )
            }
            return hasCorrectAnswer
        }
        return false
    }

    //TODO maybe for later i will move this to extension
    private fun error(dataState: DataState<List<TechQuizDTO>>) = DataState.Error(
        dataState.metaData as ErrorMetaData,
        emptyList<TechQuizItemWrapper>()
    )

    //TODO maybe for later i will move this to extension
    private fun loading(dataState: DataState<List<TechQuizDTO>>) = DataState.Loading(
        dataState.metaData as LoadingMetaData,
        emptyList<TechQuizItemWrapper>()
    )

}

private const val FALSE_STR = "false"