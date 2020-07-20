package com.schmersaldemo.mango.components

import com.schmersaldemo.mango.models.JWTResponse
import com.schmersaldemo.mango.network.*

/**
 * Author - Puneet Bahuguna
 * @Description: Helper class for network calls, Contains all network calls method. from here we call
 * the api endpoints defined in api interface.
 */
class NetworkCallsRepository {
    val apiInterface = ApiClient.client.create(ApiInterface::class.java)

    fun registerUser(bodyData:JWTResponse,responseListener: ResponseListener<Any>){
        val call = apiInterface.registerUser(bodyData)
        CallsHandler(responseListener).callRequest(call, NetworkRequestCode().REGISTER_REQUEST_CODE)
    }

    fun authenticateUser(bodyData:JWTResponse,responseListener: ResponseListener<Any>){
        val call = apiInterface.authenticateUser(bodyData)
        CallsHandler(responseListener).callRequest(call, NetworkRequestCode().LOGIN_REQUEST_CODE)
    }

    fun getRoles(header:String,responseListener: ResponseListener<Any>){
        val call = apiInterface.getRoles(header)
        CallsHandler(responseListener).callRequest(call, NetworkRequestCode().ROLES_REQUEST_CODE)
    }
    fun getCustomerMapping(header:String,responseListener: ResponseListener<Any>){
        val call = apiInterface.getCustomerMapping(header)
        CallsHandler(responseListener).callRequest(call, NetworkRequestCode().MAPPING_REQUEST_CODE)
    }
    fun getCustomer(header:String,responseListener: ResponseListener<Any>){
        val call = apiInterface.getCustomer(header)
        CallsHandler(responseListener).callRequest(call, NetworkRequestCode().CUSTOMER_REQUEST_CODE)
    }
    fun getCustomerSAP(responseListener: ResponseListener<Any>){
        val call = apiInterface.getCustomerSAP()
        CallsHandler(responseListener).callRequest(call, NetworkRequestCode().CUSTOMER_SAP_REQUEST_CODE)
    }
}