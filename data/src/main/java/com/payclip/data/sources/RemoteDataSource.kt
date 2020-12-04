package com.payclip.data.sources

import com.payclip.data.models.Result
import com.payclip.domain.Video

interface RemoteDataSource {

    suspend fun searchVideo(apiKey: String, title: String): Result<List<Video>>
    suspend fun getPopularVideo(apiKey: String): Result<List<Video>>
    suspend fun getHomeVideo(apiKey: String): Result<List<Video>>

}
