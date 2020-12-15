package com.payclip.examplecleancode.ui.search

import com.payclip.examplecleancode.R
import com.payclip.examplecleancode.arch.BaseFragment
import com.payclip.examplecleancode.arch.UiState
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<SearchUI, SearchViewModel>(R.layout.search_fragment) {

    override val viewModel: SearchViewModel by viewModel()

    override fun updateUi(state: UiState) {

    }
}