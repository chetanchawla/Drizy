package com.example.divyansh.drizy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

/**
 * Created by Divyansh on 14-08-2017.
 */

public class checkLogIn extends Activity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    public String macid= getMac();

    public static String ssid;
    public static String pass;
    public static boolean gotIt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_login);

    String naam= "Device Log-In/"+macid;
    DatabaseReference myRef = database.getReference(naam);
        myRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            //displayLocation2("in second listener");
            if(dataSnapshot.getValue()!= null) {

                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String[] value= new String[2];
                String[] check= new String[2];
                int i=0;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //  displayLocation("in for");
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    value[i] = postSnapshot.getValue(String.class);
                    check[i] = postSnapshot.getKey();


                    //if already signed in
                    if (check[i].equals("ssid"))
                        ssid = value[i];
                    else
                        pass = value[i];
                i++;
                }
                    gotIt=true;
                    startActivity(new Intent(getApplicationContext(), WannaSignIn.class));
                    finish();


            }
            else {//if not yet signed-in
                //displayLocation("in else");
                //getSet();
                gotIt=false;
                startActivity(new Intent(getApplicationContext(), signInLoad.class));
                finish();
            }
            //displayLocation(ssid);
            //displayLocation2(pass);
        }

        @Override
        public void onCancelled(DatabaseError error) {
            // Failed to read value


        }
    });
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
    public static boolean returnId()
    {
        return gotIt;
    }
    public static String returnSSID()
    {
        return ssid;
    }
    public static String returnPass()
    {
        return pass;
    }
}


