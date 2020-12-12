package com.payclip.usecases

import com.payclip.data.repository.VideoRepository

class GetPopularVideosUC(private val videoRepository: VideoRepository) {

    operator fun invoke() =
        videoRepository.getPopularVideo()

}
