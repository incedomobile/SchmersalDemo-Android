package com.schmersaldemo.mango.sapcustomers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.schmersaldemo.mango.R
import com.schmersaldemo.mango.data.entity.Customer

/***
Author: Puneet Bahuguna
 ***/

class SAPCustomerListHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_sap, parent, false)) {

    private var customername:TextView?=null
    private var customernumber:TextView?=null

    init {
        customername = itemView.findViewById(R.id.customername)
        customernumber = itemView.findViewById(R.id.customernumber)
    }
    fun bind(customer:CustomerSAP) {
        customername?.text = customer.customerName
        customernumber?.text= customer.customerNumber.toString()
    }
    }