package com.schmersaldemo.mango.network

import com.schmersaldemo.mango.data.entity.Customer
import com.schmersaldemo.mango.data.entity.CustomerMapping
import com.schmersaldemo.mango.data.entity.User
import com.schmersaldemo.mango.data.entity.UserRole
import com.schmersaldemo.mango.models.CustomerMappingModel
import com.schmersaldemo.mango.models.CustomerModel
import com.schmersaldemo.mango.models.JWTResponse
import com.schmersaldemo.mango.sapcustomers.SAPCustomerModel
import com.schmersaldemo.mango.sapcustomers.SAPCustomerResponseModel
import com.schmersaldemo.mango.settings.Preferences
import retrofit2.Call
import retrofit2.http.*

/**
 *
 * Author: Puneet Bahuguna.
 * Year: 2020
 * Description: This component class is for calling each of the web apis..
 */

interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST("register")
    fun registerUser(@Body userData: JWTResponse):Call<JWTResponse>

    @Headers("Content-Type: application/json")
    @POST("authenticate")
    fun authenticateUser(@Body userData: JWTResponse):Call<JWTResponse>

    @GET("getRoles")
    fun getRoles(@Header("Authorization") token:String):Call<List<UserRole>>

    @GET("getcustomerMapping")
    fun getCustomerMapping(@Header("Authorization") token:String):Call<CustomerMappingModel>

    @GET("getcustomers")
    fun getCustomer(@Header("Authorization") token:String):Call<CustomerModel>

    @GET("sap/getGermanCustomers")
    fun getCustomerSAP():Call<SAPCustomerResponseModel>

}
