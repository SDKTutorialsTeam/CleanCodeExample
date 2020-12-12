package com.payclip.examplecleancode.server

import com.payclip.data.sources.RemoteDataSource
import com.payclip.domain.Video
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.io.IOException

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
        try {
            youtubeApi.create()
                .Search().runCatching {
                    val search = this.list("id,snippet")
                        .setType("video")
                        .setMaxResults(maxVideoCount)
                        .setSafeSearch("strict")
                        .setVideoDuration("medium")
                        .setFields(fields)
                        .setKey(apiKey)
                        .setQ("videos para niños|$title")

                    withContext(Dispatchers.IO) {
                        search.execute()
                    }
                }.onSuccess {
                    //emit(it.items)
                    println("YoutubeRemoteDataSource.searchVideo --> ${it.items.toList()}")
                }.onFailure {
                    it.printStackTrace()
                    emit(listOf<Video>())
                }
        } catch (e: IOException) {
            e.printStackTrace()
            emit(listOf<Video>())
        }
    }

}
