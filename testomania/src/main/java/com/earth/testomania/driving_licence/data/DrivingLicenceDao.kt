package com.earth.testomania.driving_licence.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.earth.testomania.driving_licence.data.entity.DrivingLicenceQuestionEntity

@Dao
interface DrivingLicenceDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertQuestions(items: List<DrivingLicenceQuestionEntity>)

    @Query("DELETE FROM drivinglicencequestionentity WHERE 1 ")
    suspend fun deleteAllQuestions()

    @Query("SELECT * From drivinglicencequestionentity LIMIT :count")
    suspend fun getQuestionsForTest(count: Int): List<DrivingLicenceQuestionEntity>

    @Query("SELECT * From drivinglicencequestionentity WHERE id = :id")
    suspend fun getSingleQuestions(id: Int): DrivingLicenceQuestionEntity?

}