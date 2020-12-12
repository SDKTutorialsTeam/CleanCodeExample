package com.payclip.data.repository

import com.payclip.data.sources.RemoteDataSource
import com.payclip.domain.Video
import kotlinx.coroutines.flow.Flow

class VideoRepository(private val remoteDataSource: RemoteDataSource, private val apiKey: String) {

    fun searchVideo(title: String): Flow<List<Video>> {
        return remoteDataSource.searchVideo(apiKey, title)
    }

    fun getPopularVideo(): Flow<List<Video>> {
        return remoteDataSource.getPopularVideo(apiKey)
    }

    fun getHomeVideo(): Flow<List<Video>> {
        return remoteDataSource.getHomeVideo(apiKey)
    }

}
