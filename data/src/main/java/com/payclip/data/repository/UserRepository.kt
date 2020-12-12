package com.payclip.data.repository

import com.payclip.data.sources.LocalDataSource
import com.payclip.domain.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val localDataSource: LocalDataSource) {

    fun getUser(): Flow<User?> = localDataSource.getUser()

    suspend fun updateUser(user: User): Boolean = localDataSource.updateUser(user)

    suspend fun saveUser(user: User): Boolean = localDataSource.saveUser(user)

    suspend fun removeUser(): Boolean = localDataSource.removeUser()

}
