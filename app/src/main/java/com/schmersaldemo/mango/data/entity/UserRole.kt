package com.schmersaldemo.mango.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/***
Author: Puneet Bahuguna
@Description: Entity class for role table in the database.
 ***/
@Entity(tableName = "role")
data class UserRole(
    @PrimaryKey val roleid:Int,
    @ColumnInfo(name = "role") val role: String?,
    @ColumnInfo(name = "role_de") val role_de: String?
    )