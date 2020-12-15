package com.payclip.examplecleancode.server

import android.content.Context
import com.google.android.gms.auth.GoogleAuthUtil
import com.payclip.data.sources.RemoteDataSource
import com.payclip.domain.Access
import com.payclip.domain.Channel
import com.payclip.domain.Video
import com.payclip.examplecleancode.extensions.toChannelList
import com.payclip.examplecleancode.extensions.toVideoList
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.resume

class YoutubeRemoteDataSource(private val context: Context, private val youtubeApi: YoutubeApi) : RemoteDataSource {

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

    override fun getSubscription(apiKey: String): Flow<List<Channel>> = flow {
        try {
            youtubeApi.create()
                .Channels().runCatching {
                    val channels = this.list("id,contentDetails")
                        .setMine(true)
                        .setFields("items/contentDetails,nextPageToken,pageInfo")
                        .setKey(apiKey)

                    withContext(Dispatchers.IO) {
                        channels.execute()
                    }
                }.onSuccess {
                    emit(it.toChannelList())
                }.onFailure {
                    it.printStackTrace()
                    emit(listOf<Channel>())
                }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(listOf<Channel>())
        }
    }

    override fun getVideosBySubscription(apiKey: String, playlistId: String): Flow<List<Video>> = flow {
        try {
            youtubeApi.create()
                .PlaylistItems().runCatching {
                    val videos = this.list("id,contentDetails,snippet")
                        .setPlaylistId(playlistId)
                        .setFields("items(contentDetails/videoId,snippet/title,snippet/publishedAt),nextPageToken,pageInfo")
                        .setKey(apiKey)

                    withContext(Dispatchers.IO) {
                        videos.execute()
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
                        .setFields("items(id/kind,id/videoId,snippet/channelId,snippet/title,snippet/thumbnails/medium/url,snippet/channelTitle)")
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
