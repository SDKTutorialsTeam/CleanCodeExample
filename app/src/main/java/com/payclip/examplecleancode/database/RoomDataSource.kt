package com.payclip.examplecleancode.database

import com.payclip.data.sources.LocalDataSource
import com.payclip.domain.User
import com.payclip.examplecleancode.extensions.toUser
import com.payclip.examplecleancode.extensions.toUserDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RoomDataSource(db: UserDataBase) : LocalDataSource {

    private val userDao = db.userDao()

    override fun getUser(): Flow<User?> = flow {
        try {
            userDao.get().collect {
                emit(it?.toUser())
            }
        } catch (e: Exception) {
            emit(null)
        }
    }

    override suspend fun saveUser(user: User): Boolean = withContext(Dispatchers.IO) {
        try {
            userDao.insert(user.toUserDb())
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
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

