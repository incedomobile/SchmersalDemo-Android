package com.schmersaldemo.mango.sapcustomers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.schmersaldemo.mango.data.entity.Customer
import com.schmersaldemo.mango.sapcustomers.SAPCustomerListHolder

/***
Author: Puneet Bahuguna
 ***/
class SAPCustomerListAdapter (private val list: List<CustomerSAP>): RecyclerView.Adapter<SAPCustomerListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SAPCustomerListHolder {
        val inflater=LayoutInflater.from(parent.context)
        return SAPCustomerListHolder(inflater,parent)
    }

    override fun onBindViewHolder(holder: SAPCustomerListHolder, position: Int) {
    val customer:CustomerSAP=list[position]
        holder.bind(customer)
    }
    override fun getItemCount(): Int=list.size

}