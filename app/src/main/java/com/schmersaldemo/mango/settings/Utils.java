package com.schmersaldemo.mango.settings;

import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import com.schmersaldemo.MangoApplication;
import com.schmersaldemo.mango.R;
import com.schmersaldemo.mango.view.AddLoginAccount;
import com.schmersaldemo.mango.view.Login;
import java.util.Arrays;
import java.util.List;

/***
 Author: Puneet Bahuguna
 Description: Common utility methods.
 ***/
public class Utils {

    //Method for setting the effects of new selected language
    public static boolean setNewLocale(Context context,String language, boolean restartProcess) {
        MangoApplication.localeManager.setNewLocale(context, language);
        if(context instanceof AddLoginAccount){
            context.startActivity(new Intent(context, AddLoginAccount.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        }else{
            context.startActivity(new Intent(context, Login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        }
        if (restartProcess) {
            System.exit(0);
        }
        return true;
    }

    public static List<String> getLanguageCode(){
        return Arrays.asList("ES-EN","PT-BR");
    }
    public static List<String> getRoles(Context context){
        return Arrays.asList(context.getString(R.string.selectrole),context.getString(R.string.globaladmin),context.getString(R.string.localadmin),
                context.getString(R.string.consulting),context.getString(R.string.backoffice),
                context.getString(R.string.manager));
    }

    //Method convert plain text and returns into base 64 string
    public static String getBase64Encoding(String plainText){
        return Base64.encodeToString(plainText.getBytes(),1);
    }
    //Method convert base 64 text and returns into plain original string
    public static String getBase64Decoding(String encryptString){
        return new String(Base64.decode(encryptString,1));
    }
}
