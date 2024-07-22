package com.example.todolist_brief.data.room.repositories

import com.example.todolist_brief.data.room.daos.OTPCodeDao
import com.example.todolist_brief.data.room.daos.UserDao
import com.example.todolist_brief.data.room.models.OTPCode
import com.example.todolist_brief.data.room.models.User
import kotlinx.coroutines.flow.Flow

class UserRepository(
    private val userDao: UserDao

) {
   val usersList = userDao.usersList()
    suspend fun register(user: User) {userDao.register(user)}

    fun getUserByEmail(email: String): Flow<List<User>> = userDao.getUserByEmail(email)
    fun activateUserByEmail(email: String) = userDao.activateUserByEmail(email)


}