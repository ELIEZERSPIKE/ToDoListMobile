package com.example.todolist_brief.data.room.database

import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Database
import com.example.todolist_brief.data.room.daos.OTPCodeDao
import com.example.todolist_brief.data.room.daos.UserDao
import com.example.todolist_brief.data.room.models.OTPCode
import com.example.todolist_brief.data.room.models.User

@Database(
    entities = [User::class, OTPCode::class],
    version = 2,
    exportSchema = false
)
abstract class TodoListDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun otpCodeDao(): OTPCodeDao

    companion object {
        @Volatile
        var INSTANCE: TodoListDatabase? = null
        fun getDatabase(context: Context): TodoListDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context = context,
                    TodoListDatabase::class.java,
                    name = "todolist_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}