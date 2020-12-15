package com.payclip.examplecleancode.ui.home

import com.payclip.domain.Video
import com.payclip.examplecleancode.arch.ActionState
import com.payclip.examplecleancode.arch.UiState

sealed class HomeAction : ActionState {
    object ObtainHomeVideos : HomeAction()
}

sealed class HomeUI : UiState {
    object Loading: HomeUI()
    data class RenderVideos(val videoList: List<Video>): HomeUI()
}
