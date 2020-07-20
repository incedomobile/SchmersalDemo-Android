package com.schmersaldemo.mango.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/***
Author: Puneet Bahuguna
@Description: Entity class for map_customer table in the database.
 ***/
@Entity(tableName = "map_customer")
data class CustomerMapping(
    @PrimaryKey val mapid:Int,
    @ColumnInfo(name = "customerid") val customerid: Int?,
    @ColumnInfo(name = "roleid") val roleid: Int?
    )