package com.schmersaldemo.mango.models

import com.google.gson.annotations.SerializedName
import com.schmersaldemo.mango.data.entity.CustomerMapping
/***
Author: Puneet Bahuguna
@Description: data model classes for parsing customer mapping data.
 ***/
data class CustomerMappingModel (
    @SerializedName("mapCustomers")
    val mappingData: List<CustomerMapping>?=null
)
