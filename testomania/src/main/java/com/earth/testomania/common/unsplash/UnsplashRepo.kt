package com.earth.testomania.common.unsplash

import com.earth.testomania.common.data.DataState
import com.earth.testomania.common.data.ErrorMetaData
import com.earth.testomania.common.data.SuccessMetaData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class UnsplashRepo @Inject constructor(private val api: UnsplashApi) {

    suspend fun getPhoto(query: String): Flow<DataState<String>> = flow {
        api.searchPhotos(query).apply {
            if (isSuccessful) {
                try {
                    val photoUrl = body()?.results?.get(1)?.urls?.regular
                    if (photoUrl != null)
                        emit(DataState.Success(SuccessMetaData(), photoUrl))
                    else
                        emit(DataState.Error(payload = "can't fetch photo url"))
                } catch (e: Exception) {
                    emit(DataState.Error(ErrorMetaData(e)))
                }
            } else emit(DataState.Error(ErrorMetaData(null)))
        }
    }
}