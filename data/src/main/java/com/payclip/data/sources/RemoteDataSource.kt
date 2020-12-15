package com.payclip.data.sources

import com.payclip.domain.Access
import com.payclip.domain.Channel
import com.payclip.domain.Video
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun requestYoutubeAccess(): Flow<Access>
    fun searchVideo(apiKey: String, title: String): Flow<List<Video>>
    fun getPopularVideo(apiKey: String): Flow<List<Video>>
    fun getSubscription(apiKey: String): Flow<List<Channel>>
    fun getVideosBySubscription(apiKey: String, playlistId: String): Flow<List<Video>>

}
