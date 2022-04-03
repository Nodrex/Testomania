package com.earth.testomania.driving_licence.di

import android.app.Application
import androidx.room.Room
import com.earth.testomania.driving_licence.data.DrivingLicenceDao
import com.earth.testomania.driving_licence.data.DrivingLicenceDatabase
import com.earth.testomania.driving_licence.data.util.Converters
import com.earth.testomania.driving_licence.data.util.MoshiParser
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
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

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
}