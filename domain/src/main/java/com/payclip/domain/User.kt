package com.payclip.domain

data class User(
    val id: Int = -1,
    val name: String = "",
    val accountName: String,
    val avatar: Avatar = Avatar.KEVIN,
    val lastVideoWatched: String? = null
)
