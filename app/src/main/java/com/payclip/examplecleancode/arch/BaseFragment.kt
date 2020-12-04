package com.payclip.examplecleancode.arch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import org.koin.androidx.scope.ScopeFragment

abstract class BaseFragment<UI: UiState, VM: ScopedViewModel<UI>>(@LayoutRes layout: Int) : ScopeFragment(layout) {

    abstract val viewModel: VM
    open var fragmentResultCompletion: (Intent?) -> Unit = {}

    val registerForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result?.resultCode == Activity.RESULT_OK) {
            fragmentResultCompletion(result.data)
        } else {
            fragmentResultCompletion(null)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.model.observe(this@BaseFragment, Observer(::updateUi))
    }

    abstract fun updateUi(state: UiState)

}
