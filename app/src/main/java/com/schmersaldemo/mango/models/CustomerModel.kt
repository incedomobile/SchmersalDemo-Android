package com.schmersaldemo.mango.models

import com.google.gson.annotations.SerializedName
import com.schmersaldemo.mango.data.entity.Customer
/***
Author: Puneet Bahuguna
@Description: data model classes for parsing customer data.
 ***/
data class CustomerModel (@SerializedName("customers")
                          val customerData: List<Customer>?=null)