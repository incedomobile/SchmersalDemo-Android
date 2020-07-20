package com.schmersaldemo.mango.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.text.trimmedLength
import com.schmersaldemo.mango.R
import com.schmersaldemo.mango.components.NetworkCallsRepository
import com.schmersaldemo.mango.localization.BaseActivity
import com.schmersaldemo.mango.models.JWTResponse
import com.schmersaldemo.mango.network.ResponseListener
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.progress_layout.*

class Register : BaseActivity(), ResponseListener<Any> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar!!.title=getString(R.string.registeruser)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        register.setOnClickListener {
            if(loginpassword.text!!.trimmedLength()<1 ||loginusername.text!!.trimmedLength()<1){
                Toast.makeText(this,getString(R.string.inpuuterror), Toast.LENGTH_SHORT).show()
            }else if(rolespinner.selectedItemPosition==0){
                Toast.makeText(this,getString(R.string.selectrole), Toast.LENGTH_SHORT).show()
            }else{
                pbText.setText(getString(R.string.register))
                llProgressBar.visibility= View.VISIBLE
                NetworkCallsRepository().registerUser(
                    JWTResponse(loginusername.text.toString(),
                        loginpassword.text.toString(), rolespinner.selectedItemPosition.toLong()
                    ),this)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                super.onBackPressed()
            }
        }
        return true
    }

    override fun onSuccess(requestCode: Int, response: Any?) {
        llProgressBar.visibility= View.GONE
        loginusername.setText("")
        loginpassword.setText("")
        rolespinner.setSelection(0)
        Toast.makeText(this,getString(R.string.registersuccess),Toast.LENGTH_SHORT).show();
    }

    override fun onError(requestCode: Int, response: Any?) {
        llProgressBar.visibility= View.GONE
        Toast.makeText(this,getString(R.string.networkerror),Toast.LENGTH_SHORT).show();
    }

    override fun onFailure(requestCode: Int, thr: Throwable) {
        llProgressBar.visibility= View.GONE
        Toast.makeText(this,getString(R.string.networkerror),Toast.LENGTH_SHORT).show();
    }
}
