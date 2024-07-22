package com.example.todolist_brief.data.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todolist_brief.data.room.models.OTPCode
import com.example.todolist_brief.data.room.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun register(user: User)

    @Query("SELECT * FROM users")
    fun usersList() : Flow<List<User>>
    @Query("SELECT * FROM users WHERE email =:email AND  state = 1")
    fun getUserByEmail(email: String): Flow<List<User>>

    @Query("UPDATE users SET state = 1 WHERE email =:email ")
    fun activateUserByEmail(email: String)
}
