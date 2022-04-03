package com.earth.testomania.driving_licence.di

import android.app.Application
import androidx.room.Room
import com.earth.testomania.driving_licence.data.DrivingLicenceDao
import com.earth.testomania.driving_licence.data.DrivingLicenceDatabase
import com.earth.testomania.driving_licence.data.repository.DrivingLicenceRepoImpl
import com.earth.testomania.driving_licence.data.util.Converters
import com.earth.testomania.driving_licence.data.util.MoshiParser
import com.earth.testomania.driving_licence.domain.repository.DrivingLicenceRepo
import com.earth.testomania.driving_licence.domain.use_case.GetDrivingLicenceQuestions
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DrivingLicenceModule {


    @Provides
    @Singleton
    fun provideDrivingLicenceDatabase(
        app: Application,
        moshi: Moshi
    ): DrivingLicenceDatabase {
        return Room
            .databaseBuilder(app, DrivingLicenceDatabase::class.java, "driving_licence_db")
            .addTypeConverter(MoshiParser(moshi))
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
    fun provideDrivingLicenceRepo(dao: DrivingLicenceDao): DrivingLicenceRepo =
        DrivingLicenceRepoImpl(dao)
}