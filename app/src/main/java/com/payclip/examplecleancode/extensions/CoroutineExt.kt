package com.payclip.examplecleancode.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

inline fun launchInUI(crossinline continuation: suspend CoroutineScope.() -> Unit) =
    CoroutineScope(Main).launch {
        continuation.invoke(this)
    }