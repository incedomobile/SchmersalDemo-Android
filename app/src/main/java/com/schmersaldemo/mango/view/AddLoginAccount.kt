package com.schmersaldemo.mango.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.text.trimmedLength
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.schmersaldemo.mango.R
import com.schmersaldemo.mango.localization.BaseActivity
import com.schmersaldemo.mango.localization.LocaleManager
import com.schmersaldemo.mango.settings.Preferences
import com.schmersaldemo.mango.settings.Utils
import com.schmersaldemo.mango.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.activity_add_login_account.*
import kotlinx.coroutines.launch

/***
Author: Puneet Bahuguna
 @Description: Activity for
 ***/

class AddLoginAccount : BaseActivity(), View.OnClickListener {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var userSpinner:Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_login_account)
        ok.setOnClickListener(this)
        addacc.setOnClickListener(this)
        exstlogin.setOnClickListener(this)
        loginViewModel=ViewModelProvider(this).get(LoginViewModel::class.java)
        userSpinner = findViewById(R.id.userSpinner)
        lifecycleScope.launch {
            val userList = loginViewModel.getAllLoginUsers()
            val userAdapter = ArrayAdapter(this@AddLoginAccount,android.R.layout.simple_spinner_item,userList)
            userAdapter.setDropDownViewResource(R.layout.dropdown_item)
            userSpinner.adapter=userAdapter
        }
    }
    override fun onClick(p0: View?) {
        when(p0!!.id){
           R.id.ok->{
             changeView()
           }
            R.id.addacc->{
             startActivity(Intent().setClass(this,Login::class.java))
            }
            R.id.exstlogin->{
                if(loggedinpassword.text!!.trimmedLength()<1){
                    Toast.makeText(this,getString(R.string.enterpwd),Toast.LENGTH_SHORT).show()
                }else doLocalLogin(userSpinner.selectedItem.toString(),Utils.getBase64Encoding(loggedinpassword.text.toString()))
            }
        }
    }
    fun doLocalLogin(username:String,password:String){
        lifecycleScope.launch {
            val user=loginViewModel.authenticateLocaluser(username,password)
            if(user!=null){
                Preferences.setUsername(this@AddLoginAccount,user.username.toString())
                Preferences.setuserRoleID(this@AddLoginAccount,user.roleid!!.toInt())
                Preferences.setUserLogged(this@AddLoginAccount,true)
                startActivity(Intent().setClass(this@AddLoginAccount,MainActivity::class.java))
                this@AddLoginAccount.finish()
            }else{
                Toast.makeText(this@AddLoginAccount,getString(R.string.pwderror), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
    override fun onBackPressed() {
        if(btnlayout.visibility==View.INVISIBLE){
            loggedinpassword.visibility=View.INVISIBLE
            exstlogin.visibility=View.INVISIBLE
            btnlayout.visibility=View.VISIBLE
            langspinner.visibility=View.VISIBLE
        }else{
            //super.onBackPressed()
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            //finish()

        }
    }
    fun changeView(){
        loggedinpassword.visibility=View.VISIBLE
        exstlogin.visibility=View.VISIBLE
        btnlayout.visibility=View.INVISIBLE
        langspinner.visibility=View.INVISIBLE
    }
}