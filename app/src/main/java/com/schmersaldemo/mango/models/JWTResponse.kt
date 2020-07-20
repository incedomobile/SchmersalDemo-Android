package com.schmersaldemo.mango.models

import com.google.gson.annotations.SerializedName
import com.schmersaldemo.mango.data.entity.User

/***
Author: Puneet Bahuguna
@Description: data model classes for parsing User data.
 ***/
data class JWTResponse (
    @SerializedName("username")
    val username: String? = null,
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("roleid")
    val roleid: Long = 0,
    @SerializedName("userDao")
    val userData: User?=null,
    @SerializedName("token")
    val token: String? = null


)