package com.payclip.data.sources

import com.payclip.domain.User
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getUser(): Flow<User?>
    suspend fun saveUser(user: User): Boolean
    suspend fun updateUser(user: User): Boolean
    suspend fun removeUser(): Boolean

}
