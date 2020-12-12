package com.payclip.usecases

import com.payclip.data.repository.VideoRepository

class GetHomeVideosUC(private val videoRepository: VideoRepository) {

    operator fun invoke() =
        videoRepository.getHomeVideo()

}
