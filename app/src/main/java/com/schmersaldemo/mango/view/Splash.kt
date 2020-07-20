package com.schmersaldemo.mango.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import com.schmersaldemo.mango.R
import com.schmersaldemo.mango.data.entity.*
import com.schmersaldemo.mango.sapcustomers.SAPCustomersActivity
import com.schmersaldemo.mango.settings.Preferences
import com.schmersaldemo.mango.viewmodels.SyncViewModel

/***
Author: Puneet Bahuguna
 @Org - Incedo inc
 ***/

class Splash : AppCompatActivity() {

    private lateinit var syncViewModel: SyncViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        syncViewModel=ViewModelProvider(this).get(SyncViewModel::class.java)

        Handler().postDelayed(Runnable {
            //Here we check the in the shared preferences if sync data has been stored in the local DB or not
            if(Preferences.getDataSaved(this)){
                startActivity(Intent().setClass(this,AddLoginAccount::class.java))
            }else{
                startActivity(Intent().setClass(this,Login::class.java))
            }
            this@Splash.finish()
        },3000)
    }
}
