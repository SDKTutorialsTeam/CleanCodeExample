package com.payclip.examplecleancode.database

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT EXISTS(SELECT * FROM User)")
    fun exist(): Boolean

    @Query("SELECT * FROM User LIMIT 1")
    fun get(): User

    @Transaction
    fun insertAndGet(user: User): User {
        insert(user)
        return get()
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Query("DELETE FROM User")
    fun delete()

}
