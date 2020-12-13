package com.payclip.data.sources

import com.payclip.domain.Access
import com.payclip.domain.Video
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun requestYoutubeAccess(): Flow<Access>
    fun searchVideo(apiKey: String, title: String): Flow<List<Video>>
    fun getPopularVideo(apiKey: String): Flow<List<Video>>
    fun getHomeVideo(apiKey: String): Flow<List<Video>>

}
