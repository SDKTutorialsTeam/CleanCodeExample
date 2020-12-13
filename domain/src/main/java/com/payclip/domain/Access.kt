package com.payclip.domain

data class Access(
    val token: String?,
    val throwable: Throwable?
)