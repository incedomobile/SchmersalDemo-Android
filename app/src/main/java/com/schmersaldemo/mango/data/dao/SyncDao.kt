package com.schmersaldemo.mango.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.schmersaldemo.mango.data.entity.*
/***
Author: Puneet Bahuguna
 @Description: DB sync operation queries.
 ***/
@Dao
interface SyncDao{

    @Insert
    fun insertUser(vararg user: User)

    @Insert
    fun insertUserRole(userRole:List<UserRole>)

    @Insert
    fun insertCustomer(customers: List<Customer>)

    @Insert
    fun insertCustomerMapping(mapCustomer: List<CustomerMapping>)

    /*@Insert
    fun insertLanguageList(languageList: List<LanguageList>)
*/
}