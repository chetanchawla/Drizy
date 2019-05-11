package com.example.divyansh.drizy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

/**
 * Created by HP on 7/13/2017.
 */

public class intermediate extends Activity {
    public static String ID;
    public int doBool=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inter);
        ID=getMac();
        while(ID==""||ID==null||ID=="\0"){
        }
        startActivity(new Intent(getApplicationContext(), Second.class));
        //returnID();
//        doBool= Second.backKaro();
//        if(doBool==1){
        finish();
//        }
    }
    public static String getMac() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(Integer.toHexString(b & 0xFF) + ":");
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            //handle exception
        }
        return "";
    }
    public static String returnID(){
        return ID;
    }
}
