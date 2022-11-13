package com.earth.testomania.opentdb

import com.earth.testomania.opentdb.models.OpenTdbQuizDto
import retrofit2.Response
import javax.inject.Inject

class OpenTdbRepo @Inject constructor(private val api: OpenTdbQuizApi) {

    suspend fun getQuiz(category: OpenTdbCategory, questionCount: Int): Response<OpenTdbQuizDto> {
        return api.getQuizList(
            category = category.id,
            questionCount = questionCount
        )
    }

}

enum class OpenTdbCategory(val id: Int) {
    GENERAL(9),
    ENTERTAINMENT(2),
    ANIME_MANGA(31);

    enum class Entertainment(id: Int) {
        BOOKS(10),
        FILM(11),
        MUSIC(12),
        MUSICALS_AND_THEATRES(13),
        TELEVISION(14),
        VIDEO_GAMES(15),
        BOARD_GAMES(16),
    }

    enum class Science(id: Int) {
        NATURE(17),
        COMPUTERS(18),
        MATHEMATICS(19)
    }
}

// TODO add other categories
//private const val categories = """<select name="trivia_category" class="form-control">
//    <option value="any">Any Category</option>
//    <option value="9">General Knowledge</option>
//    <option value="10">Entertainment: Books</option>
//    <option value="11">Entertainment: Film</option>
//    <option value="12">Entertainment: Music</option>
//    <option value="13">Entertainment: Musicals &amp; Theatres</option>
//    <option value="14">Entertainment: Television</option>
//    <option value="15">Entertainment: Video Games</option>
//    <option value="16">Entertainment: Board Games</option>
//    <option value="17">Science &amp; Nature</option>
//    <option value="18">Science: Computers</option>
//    <option value="19">Science: Mathematics</option>
//    <option value="20">Mythology</option>
//    <option value="21">Sports</option>
//    <option value="22">Geography</option>
//    <option value="23">History</option>
//    <option value="24">Politics</option>
//    <option value="25">Art</option>
//    <option value="26">Celebrities</option>
//    <option value="27">Animals</option>
//    <option value="28">Vehicles</option>
//    <option value="29">Entertainment: Comics</option>
//    <option value="30">Science: Gadgets</option>
//    <option value="31">Entertainment: Japanese Anime &amp; Manga</option>
//    <option value="32">Entertainment: Cartoon &amp; Animations</option>
//</select>
//"""
