package com.payclip.examplecleancode.ui.popular

import androidx.lifecycle.LiveData
import com.payclip.examplecleancode.arch.ActionState
import com.payclip.examplecleancode.arch.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher

class PopularViewModel(uiDispatcher: CoroutineDispatcher) : ScopedViewModel<PopularUI>(uiDispatcher) {
    override val model: LiveData<PopularUI>
        get() = mModel

    override fun dispatch(action: ActionState) {

    }

}
