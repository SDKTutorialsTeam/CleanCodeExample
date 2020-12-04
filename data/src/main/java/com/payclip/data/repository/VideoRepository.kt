package com.payclip.data.repository

import com.payclip.data.models.Result
import com.payclip.data.sources.RemoteDataSource
import com.payclip.domain.Video

class VideoRepository(private val remoteDataSource: RemoteDataSource, private val apiKey: String) {

    suspend fun searchVideo(title: String): Result<List<Video>> {
        return remoteDataSource.searchVideo(apiKey, title)
    }

    suspend fun getPopularVideo(): Result<List<Video>> {
        return remoteDataSource.getPopularVideo(apiKey)
    }

    suspend fun getHomeVideo(): Result<List<Video>> {
        return remoteDataSource.getHomeVideo(apiKey)
    }

}
