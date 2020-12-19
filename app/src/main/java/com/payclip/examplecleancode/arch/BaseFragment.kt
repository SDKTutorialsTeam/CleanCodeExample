package com.payclip.examplecleancode.arch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.LayoutRes
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.scope.ScopeFragment

abstract class BaseFragment<UI: UiState, A: ActionState, VM: ScopedViewModel<UI, A>>(@LayoutRes layout: Int) : ScopeFragment(layout) {

    abstract val viewModel: VM
    open var fragmentResultCompletion: (Intent?) -> Unit = {}

    private lateinit var uiStateJob: Job

    val registerForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result?.resultCode == Activity.RESULT_OK) {
            fragmentResultCompletion(result.data)
        } else {
            fragmentResultCompletion(null)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uiStateJob = lifecycleScope.launch {
            viewModel.state.collect(::render)
        }
    }

    abstract fun render(state: UI)

    fun sendAction(action: A) {
        lifecycleScope.launch {
            viewModel.actionFlow.emit(action)
        }
    }

    override fun onDetach() {
        uiStateJob.cancel()
        super.onDetach()
    }

}
