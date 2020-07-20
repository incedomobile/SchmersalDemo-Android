package com.schmersaldemo.mango.components

import com.schmersaldemo.mango.data.dao.CustomerListDao
import com.schmersaldemo.mango.data.entity.Customer
/***
Author: Puneet Bahuguna
 @Description: This repository class is responsoble for connection of customer dao with view.
 ***/
class CustomerListRepository(private val customerListDao: CustomerListDao){
    suspend fun getCustomerList(roleid:Int):List<Customer>{
        return customerListDao.getCustomerList(roleid)
    }
    suspend fun getUserRole(roleid: Int):String{
        return customerListDao.getUserRole(roleid)
    }
    suspend fun getUserRolePR(roleid: Int):String{
        return customerListDao.getUserRolePR(roleid)
    }

}