package com.payclip.examplecleancode.ui.search

import com.payclip.examplecleancode.R
import com.payclip.examplecleancode.arch.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<SearchUI, SearchAction, SearchViewModel>(R.layout.search_fragment) {

    override val viewModel: SearchViewModel by viewModel()

    override fun render(state: SearchUI) {

    }
}