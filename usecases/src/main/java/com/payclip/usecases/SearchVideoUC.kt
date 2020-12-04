package com.payclip.usecases

import com.payclip.data.repository.VideoRepository

class SearchVideoUC(private val videoRepository: VideoRepository) {

    suspend operator fun invoke(title: String) =
        videoRepository.searchVideo(title)

}
