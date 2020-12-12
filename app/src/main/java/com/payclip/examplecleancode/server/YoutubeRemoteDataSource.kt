package com.payclip.examplecleancode.server

import com.payclip.data.sources.RemoteDataSource
import com.payclip.domain.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class YoutubeRemoteDataSource(private val youtubeApi: YoutubeApi) : RemoteDataSource {

    private val fields: String by lazy {
        "items(id/kind,id/videoId,snippet/channelId,snippet/title,snippet/thumbnails/medium/url,snippet/channelTitle)"
    }

    private val maxVideoCount = 50L

    override fun getHomeVideo(apiKey: String): Flow<List<Video>> = flow {

    }

    override fun getPopularVideo(apiKey: String): Flow<List<Video>> = flow {

    }

    override fun searchVideo(apiKey: String, title: String): Flow<List<Video>> = flow {

    }

}
