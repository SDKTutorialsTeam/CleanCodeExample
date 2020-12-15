package com.payclip.usecases

import com.payclip.data.repository.VideoRepository

class GetSubscriptionsUC(private val videoRepository: VideoRepository) {

    operator fun invoke() =
        videoRepository.getSubscriptions()

}
