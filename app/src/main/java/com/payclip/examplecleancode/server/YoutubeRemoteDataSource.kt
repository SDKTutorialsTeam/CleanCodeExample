package com.payclip.examplecleancode.server

import com.payclip.data.models.Result
import com.payclip.data.sources.RemoteDataSource
import com.payclip.domain.Video
import java.io.IOException

class YoutubeRemoteDataSource(private val youtubeApi: YoutubeApi) : RemoteDataSource {

    private val fields: String by lazy {
        "items(id/kind,id/videoId,snippet/channelId,snippet/title,snippet/thumbnails/medium/url,snippet/channelTitle)"
    }

    private val maxVideoCount = 50L

    override suspend fun searchVideo(apiKey: String, title: String): Result<List<Video>> {
        return try {
//            val search = youtubeSession.create()
//                .Search()
//                .list("id,snippet")
//                .setType("video")
//                .setMaxResults(maxVideoCount)
//                .setSafeSearch("strict")
//                .setVideoDuration("medium")
//                .setFields(fields)
//
//            val list = search
//                .setKey(apiKey)
//                .setQ(title)
//
//            list.execute().items
            Result.Success(listOf())
        } catch (e: IOException) {
            Result.Failure(e)
        }
    }

    override suspend fun getPopularVideo(apiKey: String): Result<List<Video>> {
        TODO("Not yet implemented")
    }

    override suspend fun getHomeVideo(apiKey: String): Result<List<Video>> {
        TODO("Not yet implemented")
    }

}
