package com.schmersaldemo.mango.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/***
Author: Puneet Bahuguna
 @Description: Entity class for Customer table in the database.
 ***/
@Entity(tableName = "customer")
data class Customer(
    @PrimaryKey val customerid:Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "address") val address: String?,
    @ColumnInfo(name = "city") val city: String?,
    @ColumnInfo(name = "country") val country: String?,
    @ColumnInfo(name = "federalstate") val federal_state: String?,
    @ColumnInfo(name = "assignedprojects") val assigned_projects: String?,
    @ColumnInfo(name = "customerinfo") val customer_info: String?

)
