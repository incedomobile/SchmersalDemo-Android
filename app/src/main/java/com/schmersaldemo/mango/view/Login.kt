package com.schmersaldemo.mango.view

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.text.trimmedLength
import androidx.lifecycle.ViewModelProvider
import androidx.room.RoomDatabase
import com.schmersaldemo.mango.R
import com.schmersaldemo.mango.components.NetworkCallsRepository
import com.schmersaldemo.mango.data.entity.Customer
import com.schmersaldemo.mango.data.entity.CustomerMapping
import com.schmersaldemo.mango.data.entity.User
import com.schmersaldemo.mango.data.entity.UserRole
import com.schmersaldemo.mango.localization.BaseActivity
import com.schmersaldemo.mango.models.CustomerMappingModel
import com.schmersaldemo.mango.models.CustomerModel
import com.schmersaldemo.mango.models.JWTResponse
import com.schmersaldemo.mango.network.NetworkRequestCode
import com.schmersaldemo.mango.network.ResponseListener
import com.schmersaldemo.mango.settings.Preferences
import com.schmersaldemo.mango.settings.Utils
import com.schmersaldemo.mango.viewmodels.SyncViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.llProgressBar
import kotlinx.android.synthetic.main.activity_login.loginpassword
import kotlinx.android.synthetic.main.activity_login.loginusername
import kotlinx.android.synthetic.main.activity_register.*

/***
Author: Puneet Bahuguna
 ***/

class Login : BaseActivity(),ResponseListener<Any> {
    //private lateinit var loginViewModel: LoginViewModel
    private lateinit var syncViewModel: SyncViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        syncViewModel=ViewModelProvider(this).get(SyncViewModel::class.java)
        login.setOnClickListener {
            if(loginpassword.text!!.trimmedLength()<1 ||loginusername.text!!.trimmedLength()<1){
                Toast.makeText(this,getString(R.string.inpuuterror),Toast.LENGTH_SHORT).show()
            }else{
                llProgressBar.visibility= View.VISIBLE
                NetworkCallsRepository().authenticateUser(JWTResponse(loginusername.text.toString(),
                    loginpassword.text.toString()),this)
            }
        }
    }

    override fun onBackPressed() {
        if(!isTaskRoot) {
           super.onBackPressed()
        }else {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }

    override fun onSuccess(requestCode: Int, response: Any?) {
        if(requestCode==NetworkRequestCode().LOGIN_REQUEST_CODE){
            val result = response as JWTResponse
            Preferences.setUsername(this,result.userData?.username!!)
            Preferences.setuserRoleID(this,result.userData.roleid!!)
            Preferences.setUserLogged(this,true)
            Preferences.setJWT(this,result.token!!)
            result.userData.password= Utils.getBase64Encoding(loginpassword.text.toString())
            syncViewModel.insertUser(result.userData)
            if(Preferences.getDataSaved(this)){
                goHome()
            }else{
                NetworkCallsRepository().getRoles("Bearer " +Preferences.getJWT(this)!!,this)
            }
        } else if(requestCode==NetworkRequestCode().ROLES_REQUEST_CODE){
            syncViewModel.insertUserRole(response as List<UserRole>)
            NetworkCallsRepository().getCustomerMapping("Bearer " +Preferences.getJWT(this)!!,this)
        }else if(requestCode==NetworkRequestCode().MAPPING_REQUEST_CODE){
            val result = response as CustomerMappingModel
            syncViewModel.insertCustomerMapping(result.mappingData!!)
            NetworkCallsRepository().getCustomer("Bearer " +Preferences.getJWT(this)!!,this)
        }else if(requestCode==NetworkRequestCode().CUSTOMER_REQUEST_CODE){
            val result = response as CustomerModel
            syncViewModel.insertCustomer(result.customerData!!)
            Preferences.setDataSaved(this,true)
            Log.d("LOG","Sync Complete")
            goHome()
        }
    }

    override fun onError(requestCode: Int, response: Any?) {
        if(requestCode==NetworkRequestCode().LOGIN_REQUEST_CODE){
            Toast.makeText(this,getString(R.string.autherror),Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,getString(R.string.networkerror),Toast.LENGTH_SHORT).show();
        }
        llProgressBar.visibility= View.GONE
        rollbackoperation()
    }

    override fun onFailure(requestCode: Int, thr: Throwable) {
        Toast.makeText(this,getString(R.string.networkerror),Toast.LENGTH_SHORT).show();
        llProgressBar.visibility= View.GONE
        rollbackoperation()
    }
    fun rollbackoperation(){
        syncViewModel.deleteAllData()
    }
    fun goHome(){
        llProgressBar.visibility= View.GONE
        startActivity(Intent().setClass(this@Login,MainActivity::class.java))
        this@Login.finish()
    }
}
