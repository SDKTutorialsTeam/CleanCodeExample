package com.payclip.usecases

import com.payclip.data.repository.VideoRepository

class GetVideosByChannelUC(private val videoRepository: VideoRepository) {

    operator fun invoke(playListId: String) =
        videoRepository.getVideosByChannel(playListId)

}
