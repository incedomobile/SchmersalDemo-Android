package com.schmersaldemo.mango.sapcustomers

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.schmersaldemo.mango.R
import com.schmersaldemo.mango.components.NetworkCallsRepository
import com.schmersaldemo.mango.localization.BaseActivity
import com.schmersaldemo.mango.network.ResponseListener
import kotlinx.android.synthetic.main.activity_sapcustomers.*
import kotlinx.android.synthetic.main.progress_layout.*

class SAPCustomersActivity : BaseActivity(), ResponseListener<Any> {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sapcustomers)
        supportActionBar!!.title=getString(R.string.menu_customerlistsap)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        showProgress()
        NetworkCallsRepository().getCustomerSAP(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                super.onBackPressed()
            }
        }
        return true
    }

    fun populateCustomerListSAP(customerList:List<CustomerSAP>){
        sapcustomerlist.apply {
            layoutManager = LinearLayoutManager(context)
            adapter=SAPCustomerListAdapter(customerList)
        }
    }

    fun showProgress(){
        pbText.setText(getString(R.string.loading))
        llProgressBar.visibility= View.VISIBLE
    }

    override fun onSuccess(requestCode: Int, response: Any?) {
        llProgressBar.visibility= View.GONE
        val result = response as SAPCustomerResponseModel
        populateCustomerListSAP(result.responsedata!!.customerData!!)
    }

    override fun onError(requestCode: Int, response: Any?) {
        llProgressBar.visibility= View.GONE
        Toast.makeText(this,getString(R.string.networkerror), Toast.LENGTH_SHORT).show();
    }

    override fun onFailure(requestCode: Int, thr: Throwable) {
        llProgressBar.visibility= View.GONE
        Toast.makeText(this,getString(R.string.networkerror),Toast.LENGTH_SHORT).show();
    }
}
