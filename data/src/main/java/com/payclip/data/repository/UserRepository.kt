package com.payclip.data.repository

import com.payclip.data.models.Result
import com.payclip.data.sources.LocalDataSource
import com.payclip.domain.User

class UserRepository(private val localDataSource: LocalDataSource) {

    suspend fun existUser(): Boolean {
        return localDataSource.existUser()
    }

    suspend fun getUser(): Result<User> {
        return localDataSource.getUser()
    }

    suspend fun updateUser(user: User): Boolean {
        return localDataSource.updateUser(user)
    }

    suspend fun saveUser(user: User): Result<User> {
        return localDataSource.saveUser(user)
    }

    suspend fun removeUser(): Boolean {
        return localDataSource.removeUser()
    }

}
