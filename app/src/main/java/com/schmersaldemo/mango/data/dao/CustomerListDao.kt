package com.schmersaldemo.mango.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.schmersaldemo.mango.data.entity.Customer
/***
Author: Puneet Bahuguna
 @Description: Database queries for the customer related data
 ***/
@Dao
interface CustomerListDao{

    @Query("SELECT DISTINCT customer.customerid,name,address,city,country,federalstate,assignedprojects,customerinfo FROM customer INNER JOIN map_customer WHERE roleid=:roleid AND map_customer.customerid=customer.customerid")
    suspend fun getCustomerList(roleid:Int):List<Customer>

    @Query("SELECT role FROM role WHERE roleid=:roleid")
    suspend fun getUserRole(roleid: Int):String

    @Query("SELECT role_de FROM role WHERE roleid=:roleid")
    suspend fun getUserRolePR(roleid: Int):String
}