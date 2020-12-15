package com.payclip.examplecleancode.server

import android.content.Context
import com.google.android.gms.auth.GoogleAuthUtil
import com.payclip.data.sources.RemoteDataSource
import com.payclip.domain.Access
import com.payclip.domain.Video
import com.payclip.examplecleancode.extensions.toVideoList
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.resume

class YoutubeRemoteDataSource(private val context: Context, private val youtubeApi: YoutubeApi) : RemoteDataSource {

    private val fields: String by lazy {
        "items(id/kind,id/videoId,snippet/channelId,snippet/title,snippet/thumbnails/medium/url,snippet/channelTitle)"
    }

    override fun requestYoutubeAccess(): Flow<Access> = flow {
        emit(
            suspendCancellableCoroutine { continuation ->
                GlobalScope.launch(Dispatchers.IO) {
                    try {
                        val token = GoogleAuthUtil.getToken(context, youtubeApi.googleAccount.selectedAccount, youtubeApi.googleAccount.scope)
                        withContext(Dispatchers.Main) {
                            continuation.resume(Access(token, null))
                        }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            continuation.resume(Access(null, e))
                        }
                    }
                }
            }
        )
    }

    override fun getHomeVideo(apiKey: String): Flow<List<Video>> = flow {
        try {
            youtubeApi.create()
                .Activities().runCatching {
                    val home = this.list("snippet,contentDetails")
                        .setHome(true)
                        .setKey(apiKey)

                    withContext(Dispatchers.IO) {
                        home.execute()
                    }
                }.onSuccess {
                    emit(it.toVideoList())
                }.onFailure {
                    it.printStackTrace()
                    emit(listOf<Video>())
                }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(listOf<Video>())
        }
    }

    override fun getPopularVideo(apiKey: String): Flow<List<Video>> = flow {
        try {
            youtubeApi.create()
                .Videos().runCatching {
                    val popular = this.list("snippet,contentDetails,statistics")
                        .setChart("mostPopular")
                        .setRegionCode("MX")
                        .setKey(apiKey)

                    withContext(Dispatchers.IO) {
                        popular.execute()
                    }
                }.onSuccess {
                    emit(it.toVideoList())
                }.onFailure {
                    it.printStackTrace()
                    emit(listOf<Video>())
                }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(listOf<Video>())
        }
    }

    override fun searchVideo(apiKey: String, title: String): Flow<List<Video>> = flow {
        try {
            youtubeApi.create()
                .Search().runCatching {
                    val search = this.list("id,snippet")
                        .setType("video")
                        .setSafeSearch("strict")
                        .setVideoDuration("medium")
                        .setFields(fields)
                        .setKey(apiKey)
                        .setQ(title)

                    withContext(Dispatchers.IO) {
                        search.execute()
                    }
                }.onSuccess {
                    emit(it.toVideoList())
                }.onFailure {
                    it.printStackTrace()
                    emit(listOf<Video>())
                }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(listOf<Video>())
        }
    }

}
