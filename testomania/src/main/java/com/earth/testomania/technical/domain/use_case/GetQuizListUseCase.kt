package com.earth.testomania.technical.domain.use_case

import com.earth.testomania.core.DataState
import com.earth.testomania.technical.data.source.remote.dto.TechQuizDTO
import com.earth.testomania.technical.domain.model.AnswerKey
import com.earth.testomania.technical.domain.model.TechQuiz
import com.earth.testomania.technical.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetQuizListUseCase @Inject constructor(
    private val quizRepository: QuizRepository
) {

    suspend operator fun invoke(): Flow<DataState<List<TechQuiz>>> {
        quizRepository.getQuizList().collectLatest {
            val quizList = it.payload?.mapNotNull { techQuiz ->
                val tmp = toTechQuiz(techQuiz)
                println(tmp)
                tmp
            }
            println(quizList)
        }
        return flow {

        }
    }

    private fun toTechQuiz(techQuizDTO: TechQuizDTO) = with(techQuizDTO) {
        val possibleAnswersList = generatePossibleAnswersList(techQuizDTO) ?: return@with null
        println(possibleAnswersList)

        val correctAnswersList =
            generateCorrectAnswersList(techQuizDTO, possibleAnswersList.size) ?: return@with null
        println(correctAnswersList)

        return@with TechQuiz(
            id = id,
            question = question ?: "",
            category = category + "/" + (tags?.joinToString(separator = "/")),
            explanation = explanation ?: "",
            hasMultiAnswer = multiple_correct_answers?.toBoolean() ?: false,
            possibleAnswers = createPossibleAnswerMap(possibleAnswersList),
            correctAnswers = createCorrectAnswerMap(correctAnswersList)
        )
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

}