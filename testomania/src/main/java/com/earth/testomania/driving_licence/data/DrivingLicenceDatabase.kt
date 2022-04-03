package com.earth.testomania.driving_licence.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.earth.testomania.driving_licence.data.entity.DrivingLicenceQuestionEntity
import com.earth.testomania.driving_licence.data.util.Converters

@Database(
    entities = [DrivingLicenceQuestionEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class DrivingLicenceDatabase: RoomDatabase() {

    abstract val dao: DrivingLicenceDao
}