package com.schmersaldemo.mango.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.schmersaldemo.mango.components.SyncRepository
import com.schmersaldemo.mango.data.database.AppDatabase
import com.schmersaldemo.mango.data.entity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SyncViewModel (application: Application): AndroidViewModel(application){

    //val syncDao = AppDatabase.getDatabase(application).syncDao()
    val syncDao = AppDatabase.getDatabase(application)
    fun insertUser(user: User)=viewModelScope.launch(Dispatchers.IO) {
        SyncRepository(syncDao.syncDao()).insertUser(user)
    }
    fun insertUserRole(userRole: List<UserRole>)=viewModelScope.launch(Dispatchers.IO) {
        SyncRepository(syncDao.syncDao()).insertUserRole(userRole)
    }
    fun insertCustomer(customerList: List<Customer>)=viewModelScope.launch(Dispatchers.IO) {
        SyncRepository(syncDao.syncDao()).insertCustomer(customerList)
    }
    fun insertCustomerMapping(customerMapping: List<CustomerMapping>)=viewModelScope.launch(Dispatchers.IO) {
        SyncRepository(syncDao.syncDao()).insertCustomerMapping(customerMapping)
    }
    fun deleteAllData()=viewModelScope.launch(Dispatchers.IO){
        syncDao.clearAllTables()
    }
    /*fun insertLanguageList(languageList: List<LanguageList>)=viewModelScope.launch(Dispatchers.IO) {
        SyncRepository(syncDao).insertLanguageList(languageList)
    }*/
}