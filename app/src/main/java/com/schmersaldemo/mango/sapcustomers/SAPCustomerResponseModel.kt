package com.schmersaldemo.mango.sapcustomers

import com.google.gson.annotations.SerializedName

/***
Author: Puneet Bahuguna
 @Description: Entity class for Customer table in the database.
 ***/
data class SAPCustomerResponseModel(
    @SerializedName( "MT_MANGO_CustomerList_Response") val responsedata: SAPCustomerModel?=null
)
data class SAPCustomerModel
    (@SerializedName("customer") val customerData: List<CustomerSAP>?=null
)
data class CustomerSAP(
    @SerializedName( "customerNumber") val customerNumber: Int?,
    @SerializedName( "customerName") val customerName: String?
)
