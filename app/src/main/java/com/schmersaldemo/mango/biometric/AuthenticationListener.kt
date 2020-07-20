package com.schmersaldemo.mango.biometric

/*
* Author: Puneet Bahuguna
* @Description: define overridden method for biometric callbacks
* */
interface AuthenticationListener{
    fun onAuthenticationError(errorCode:Int, errString:CharSequence)
    fun onAuthenticationSucceeded()
    fun onAuthenticationFailed()
}