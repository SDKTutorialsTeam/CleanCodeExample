package com.payclip.examplecleancode.extensions

import com.payclip.domain.Avatar
import com.payclip.domain.User
import com.payclip.examplecleancode.database.User as UserDb

fun UserDb.toUser(): User = User(
    id,
    name,
    accountName,
    Avatar.valueOf(avatar),
    lastVideoWatched
)

fun User.toUserDb(): UserDb = UserDb(
    id,
    name,
    accountName,
    avatar.name,
    lastVideoWatched
)
