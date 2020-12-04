package com.payclip.examplecleancode.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserDataBase : RoomDatabase() {

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            UserDataBase::class.java,
            "user-youtube-db"
        ).build()
    }

    abstract fun userDao(): UserDao

}
