package com.example.divyansh.drizy;

import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by Divyansh on 26-08-2017.
 */

public class upload {
    private String ssid;
    private String pass;

    public String getSsid() {

        return ssid;
    }

    public String getPass() {
        return pass;
    }

    public void set(String a, String b){
        ssid=a;
        pass=b;

    }
}
