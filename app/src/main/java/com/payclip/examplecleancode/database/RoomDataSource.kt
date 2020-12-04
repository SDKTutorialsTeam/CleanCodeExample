package com.payclip.examplecleancode.database

import com.payclip.data.models.Result
import com.payclip.data.sources.LocalDataSource
import com.payclip.domain.User
import com.payclip.examplecleancode.extensions.toUser
import com.payclip.examplecleancode.extensions.toUserDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: UserDataBase) : LocalDataSource {

    private val userDao = db.userDao()

    override suspend fun existUser(): Boolean = withContext(Dispatchers.IO) { userDao.exist() }

    override suspend fun getUser(): Result<User> = withContext(Dispatchers.IO) {
        try {
            Result.Success(userDao.get().toUser())
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

    override suspend fun saveUser(user: User): Result<User> = withContext(Dispatchers.IO) {
        try {
            Result.Success(userDao.insertAndGet(user.toUserDb()).toUser())
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

    override suspend fun updateUser(user: User): Boolean = withContext(Dispatchers.IO) {
        try {
            userDao.update(user.toUserDb())
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun removeUser(): Boolean = withContext(Dispatchers.IO) {
        try {
            userDao.delete()
            true
        } catch (e: Exception) {
            false
        }
    }

}

