package com.payclip.examplecleancode.ui.popular

import com.payclip.domain.Video
import com.payclip.examplecleancode.arch.ActionState
import com.payclip.examplecleancode.arch.UiState

sealed class PopularAction : ActionState {
    object ObtainPopularVideos : PopularAction()
}

sealed class PopularUI : UiState {
    object Init : PopularUI()
    object Loading: PopularUI()
    data class RenderVideos(val videoList: List<Video>): PopularUI()
}
