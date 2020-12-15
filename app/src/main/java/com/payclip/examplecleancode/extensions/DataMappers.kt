package com.payclip.examplecleancode.extensions

import com.google.api.services.youtube.model.*
import com.payclip.domain.Avatar
import com.payclip.domain.Channel
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

fun ChannelListResponse.toChannelList(): List<Channel> = this.items.map {
    Channel(
        it.id,
        it.snippet.title,
        it.snippet.description,
        it.snippet.thumbnails.medium.url,
        it.contentDetails.relatedPlaylists.uploads
    )
}

fun PlaylistItemListResponse.toVideoList(): List<Video> = this.items.map {
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
