package com.schmersaldemo.mango.components

import com.schmersaldemo.mango.data.dao.UserDao
import com.schmersaldemo.mango.data.entity.User


/***
Author: Puneet Bahuguna
 @Description: Connection of view with Login DAO
 ***/
class LoginRepository(private val userDao: UserDao){

    suspend fun getLoggedInUsers():List<String>{
            return userDao.getAllLoggedInUSer()
        }
        suspend fun authenticateLocalUser(username:String,password:String):User{
            return userDao.authenticateUser(username,password)
        }

        /*suspend fun getLanguageCodes():List<String>{
            return userDao.getLanguageCodes()
        }*/
        /*fun insertUser(user:User){
            userDao.insertUser(user)
        }*/
}
