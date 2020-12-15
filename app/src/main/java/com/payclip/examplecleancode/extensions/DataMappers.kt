package com.payclip.examplecleancode.extensions

import com.google.api.services.youtube.model.ActivityListResponse
import com.google.api.services.youtube.model.SearchListResponse
import com.google.api.services.youtube.model.VideoListResponse
import com.payclip.domain.Avatar
import com.payclip.domain.User
import com.payclip.domain.Video
import com.payclip.examplecleancode.database.User as UserDb

fun UserDb.toUser(): User = User(
    id,
    name,
    accountName,
    Avatar.valueOf(avatar),
    lastVideoWatched
)

fun User.toUserDb(): UserDb = UserDb(
    id,
    name,
    accountName,
    avatar.name,
    lastVideoWatched
)

fun ActivityListResponse.toVideoList(): List<Video> = this.items.map {
    Video(
        it.id,
        it.snippet.channelId,
        it.snippet.channelTitle,
        it.snippet.title,
        it.snippet.thumbnails.medium.url
    )
}

fun VideoListResponse.toVideoList(): List<Video> = this.items.map {
    Video(
        it.id,
        it.snippet.channelId,
        it.snippet.channelTitle,
        it.snippet.title,
        it.snippet.thumbnails.medium.url
    )
}

fun SearchListResponse.toVideoList(): List<Video> = this.items.map {
    Video(
        it.id.videoId,
        it.snippet.channelId,
        it.snippet.channelTitle,
        it.snippet.title,
        it.snippet.thumbnails.medium.url
    )
}
