package com.schmersaldemo.mango.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/***
Author: Puneet Bahuguna
@Description: Entity class for user table in the database.
 ***/
@Entity(tableName = "user")
data class User(
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name = "password") var password: String?,
    @ColumnInfo(name = "roleid") val roleid: Int?
){
    @PrimaryKey(autoGenerate = true) var id:Int=1
}

