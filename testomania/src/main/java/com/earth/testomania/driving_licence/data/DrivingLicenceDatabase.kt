package com.earth.testomania.driving_licence.data

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.earth.testomania.R
import com.earth.testomania.driving_licence.data.dto.DrivingLicenceQuestionDto
import com.earth.testomania.driving_licence.data.entity.DrivingLicenceQuestionEntity
import com.earth.testomania.driving_licence.data.util.AnswersListConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.buffer
import okio.source
import javax.inject.Inject
import javax.inject.Provider

@Database(
    entities = [DrivingLicenceQuestionEntity::class],
    version = 1
)
abstract class DrivingLicenceDatabase : RoomDatabase() {

    abstract val dao: DrivingLicenceDao

    class CallBack @Inject constructor(
        private val database: Provider<DrivingLicenceDatabase>,
        private val applicationScope: CoroutineScope,
        @ApplicationContext private val appContext: Context,
        private val moshi: Moshi,
        private val converter: AnswersListConverter,
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().dao
            applicationScope.launch(Dispatchers.IO) {
                dao.insertQuestions(tests)
            }
        }

        @OptIn(ExperimentalStdlibApi::class)
        private val tests by lazy {
            val rawJson = appContext.resources.openRawResource(R.raw.driving_licence_tests)
            val adapter: JsonAdapter<List<DrivingLicenceQuestionDto>> = moshi.adapter()
            adapter.fromJson(rawJson.source().buffer())?.map { dto ->
                dto.toDrivingLicenceEntity(converter)
            } ?: listOf()
        }
    }
}