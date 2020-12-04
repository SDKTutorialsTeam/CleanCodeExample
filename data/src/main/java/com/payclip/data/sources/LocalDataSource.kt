package com.payclip.data.sources

import com.payclip.data.models.Result
import com.payclip.domain.User

interface LocalDataSource {

    suspend fun existUser(): Boolean
    suspend fun getUser(): Result<User>
    suspend fun saveUser(user: User): Result<User>
    suspend fun updateUser(user: User): Boolean
    suspend fun removeUser(): Boolean

}
