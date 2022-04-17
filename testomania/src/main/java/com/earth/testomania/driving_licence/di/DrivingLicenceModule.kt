package com.earth.testomania.driving_licence.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.earth.testomania.driving_licence.data.DrivingLicenceDao
import com.earth.testomania.driving_licence.data.DrivingLicenceDatabase
import com.earth.testomania.driving_licence.data.repository.DrivingLicenceRepoImpl
import com.earth.testomania.driving_licence.data.util.AnswersListConverter
import com.earth.testomania.driving_licence.data.util.JsonParser
import com.earth.testomania.driving_licence.data.util.MoshiParser
import com.earth.testomania.driving_licence.domain.repository.DrivingLicenceRepo
import com.earth.testomania.driving_licence.domain.use_case.GetDrivingLicenceQuestions
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DrivingLicenceModule {


    @Provides
    @Singleton
    fun provideDrivingLicenceDatabase(
        app: Application,
        callBack: DrivingLicenceDatabase.CallBack
    ): DrivingLicenceDatabase {
        return Room
            .databaseBuilder(app, DrivingLicenceDatabase::class.java, "driving_licence_db")
            .addCallback(callBack)
            .build()
    }


    @Provides
    @Singleton
    fun provideDrivingLicenceDao(db: DrivingLicenceDatabase): DrivingLicenceDao {
        return db.dao
    }

    @Provides
    @Singleton
    fun provideGetDrivingLicenceQuestions(repo: DrivingLicenceRepo): GetDrivingLicenceQuestions =
        GetDrivingLicenceQuestions(repo)

    @Provides
    @Singleton
    fun provideDrivingLicenceRepo(converter: AnswersListConverter, dao: DrivingLicenceDao): DrivingLicenceRepo =
        DrivingLicenceRepoImpl(converter, dao)

    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

    @Provides
    @Singleton
    fun provideContext(app: Application): Context {
        return app
    }

    @Provides
    @Singleton
    fun provideJsonParser(moshi: Moshi): JsonParser {
        return MoshiParser(moshi)
    }

    @Provides
    @Singleton
    fun provideAnswersListConverter(jsonParser: JsonParser): AnswersListConverter {
        return AnswersListConverter(jsonParser)
    }
}