package com.payclip.examplecleancode.extensions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

inline fun <T> Flow<T>.collectOnMain(
    crossinline action: suspend (value: T) -> Unit
) {
    GlobalScope.launch(Dispatchers.Main) {
        collect { action(it) }
    }
}