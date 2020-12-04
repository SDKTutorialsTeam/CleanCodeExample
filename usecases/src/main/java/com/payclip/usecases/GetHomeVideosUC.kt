package com.payclip.usecases

import com.payclip.data.repository.VideoRepository

class GetHomeVideosUC(private val videoRepository: VideoRepository) {

    suspend operator fun invoke() =
        videoRepository.getHomeVideo()

}
