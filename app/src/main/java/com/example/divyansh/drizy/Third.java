package com.example.divyansh.drizy;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static android.widget.Toast.LENGTH_LONG;
import static com.example.divyansh.drizy.R.id.lol;


/**
 * Created by HP on 7/13/2017.
 */

public class Third extends Activity {
    public String konHTu;
    public String pass;
    private LocationManager lm;                 //sets a ocation manager named lm
    private LocationListener locationListener;  //sets a location listener named locationListener
    public int Road = -1;
    public int Road1 = Road;
    public int K_Spot = -1;
    public int K_Spot1 = K_Spot;
    public float x2, x1, y2, y1;
    public int Direction = -1;
    public int countt = 0;
    public double extx2 = -1;
    public double extx1 = -1;
    public double exty1 = -1;
    public double exty2 = -1;
    public int HaiPrev = -1;
    public double prevLat = -1;
    public double prevLong = -1;
    public int[] returnInsideThing = new int[3];
    public String updatingAdd = "database1/";
    public hmm hotspot = new hmm();
    public location Loc = new location();
    public int st=1;
    int count =0;
    int doCheck=1;
    Button spot;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);
        Toast.makeText(this,"came in third",LENGTH_LONG).show();
        spot=(Button) findViewById(R.id.button8);
        boolean kisSe = checkLogIn.returnId();

        if(kisSe)
        {
            konHTu=checkLogIn.returnSSID();

            pass=checkLogIn.returnPass();
        }
        else{

            upload one = load.getObj2();

//            if (one.getSsid()== null) {
//                //one = load.getObj2();
//                Toast.makeText(this,"Confirm your username and password",LENGTH_LONG).show();
//                startActivity(new Intent(getApplicationContext(), signIn.class));
//                finish();
//            }
            konHTu = one.getSsid();
                //konHTu=signIn.getSSID();
           // Toast.makeText(this,konHTu,Toast.LENGTH_LONG).show();
            pass=one.getPass();
            //pass= signIn.getPass();
        }
        if(konHTu== null)
        {
            finish();
        }
        setHotspotName(konHTu,pass,this);
       // setHotspotName("timmy","ithastowork",this);
        //konHTu = naamLe.naamDe();
        chalao();
    }
    @Override
    protected void onDestroy() {

        FirebaseDatabase database3 = FirebaseDatabase.getInstance();
        DatabaseReference myRef3 = database3.getReference(updatingAdd + konHTu);
        myRef3.removeValue();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.runFinalizersOnExit(true);
        System.exit(0);
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onDestroy();
        finish();

    }
    public boolean setHotspotName(String newName,String password, Context context) {
        try {
            //to change ssid and password and store the pre saved one
            WifiManager wifiManager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
            Method getConfigMethod = wifiManager.getClass().getMethod("getWifiApConfiguration");
            WifiConfiguration wifiConfig = (WifiConfiguration) getConfigMethod.invoke(wifiManager);
            String ssid = wifiConfig.SSID;

            String pass = wifiConfig.preSharedKey;
            //Toast.makeText(this,ssid+" "+pass, LENGTH_LONG).show();

            wifiConfig.SSID = newName;
            wifiConfig.preSharedKey= password;
            //wifiManager.;
            Method setConfigMethod = wifiManager.getClass().getMethod("setWifiApConfiguration", WifiConfiguration.class);
            setConfigMethod.invoke(wifiManager, wifiConfig);


            //to open the hotspot intent if not already on

            boolean isWifiAPenabled = false;
            Method[] wmMethods = wifiManager.getClass().getDeclaredMethods();
            for (Method method: wmMethods) {
                if (method.getName().equals("isWifiApEnabled")) {

                    try {
                        isWifiAPenabled = (boolean)method.invoke(wifiManager);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(!isWifiAPenabled) {
                Toast.makeText(this,"Please switch on the hotspot to proceed.",LENGTH_LONG).show();
                final Intent intent = new Intent(Intent.ACTION_MAIN, null);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                final ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.TetherSettings");
                intent.setComponent(cn);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
            for (Method method: wmMethods) {
                if (method.getName().equals("isWifiApEnabled")) {

                    try {
                        isWifiAPenabled = (boolean)method.invoke(wifiManager);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(!isWifiAPenabled)
            {
                Toast.makeText(this,"System cant work",LENGTH_LONG).show();
            }
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean checkHot(Context context)
    {
        try{
        WifiManager wifiManager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
        Method getConfigMethod = wifiManager.getClass().getMethod("getWifiApConfiguration");
        WifiConfiguration wifiConfig = (WifiConfiguration) getConfigMethod.invoke(wifiManager);
            Method setConfigMethod = wifiManager.getClass().getMethod("setWifiApConfiguration", WifiConfiguration.class);
            setConfigMethod.invoke(wifiManager, wifiConfig);


            //to open the hotspot intent if not already on

            boolean isWifiAPenabled = false;
            Method[] wmMethods = wifiManager.getClass().getDeclaredMethods();
            for (Method method: wmMethods) {
                if (method.getName().equals("isWifiApEnabled")) {

                    try {
                        isWifiAPenabled = (boolean)method.invoke(wifiManager);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(!isWifiAPenabled)
            {
                //Toast.makeText(this,"System cant work",LENGTH_LONG).show();
                return false;
            }
            return true;
    }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public void generateHot(View view){
        setHotspotName(konHTu,pass,this);
    }
    public void chalao() {
        final Handler h = new Handler();

        final int delay = 1000; //milliseconds
        h.postDelayed(new Runnable() {
            public void run() {
                if(st!=0){
                    if(doCheck==1)
                    {
                        if(count<30)
                        {
                            if(checkHot(getBaseContext())){
                                doCheck=0;
                                spot.setEnabled(false);
                                spot.setVisibility(View.GONE);
                            }
                            else
                            {
                                count++;
                                Toast.makeText(getBaseContext(),"Enable the hotspot, or the app will terminate",LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            finish();
                        }
                    }
                chalio();
                }
                h.postDelayed(this, delay);
            }
        }, delay);
    }

    public void chalio() {

        lm = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener();
        Location loc = null;

        try {
            lm.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0,
                    0,
                    locationListener);
            loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        } catch (SecurityException e) {
            Toast.makeText(getBaseContext(),
                    "Can't access GPS values",
                    Toast.LENGTH_SHORT).show();
        }


        if (loc != null) {

            if (HaiPrev != -1) {
                prevLat = Loc.getLat();
                prevLong = Loc.getLong();
            }

            Loc.setLat(loc.getLatitude());
            Loc.setAlt(loc.getAltitude());
            Loc.setLong(loc.getLongitude());
            Loc.setSpeed(loc.getSpeed());
            Loc.setDirection(Direction);
            if (HaiPrev == -1) {
                prevLat = Loc.getLat();
                prevLong = Loc.getLong();
                HaiPrev = 1;
            }

            if (Road != -1) {
                if (hotspot.Road(Loc.getLat(), Loc.getLong(), Road) == -1) {
                    returnInsideThing = hotspot.FindRoad(Loc.getLat(), Loc.getLong(), prevLat, prevLong);
                    Road = returnInsideThing[1];
                    Direction = returnInsideThing[2];
                    Loc.setDirection(Direction);
                    K_Spot = returnInsideThing[0];
                } else {
                    Direction = hotspot.checkDistance(Loc.getLat(), Loc.getLong(), prevLat, prevLong);
                    Loc.setDirection(Direction);
                }

            } else {
                returnInsideThing = hotspot.FindRoad(Loc.getLat(), Loc.getLong(), prevLat, prevLong);
                Road = returnInsideThing[1];
                Direction = returnInsideThing[2];
                Loc.setDirection(Direction);
                K_Spot = returnInsideThing[0];
            }
            if (K_Spot != -1) {
                if (K_Spot1 != K_Spot) {

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference(updatingAdd + konHTu + "/Location");
                    myRef.removeValue();
                    K_Spot1 = K_Spot;
                }
                else{
                    if(Road1!= Road){
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference(updatingAdd + konHTu + "/Location");
                        myRef.removeValue();
                        Road1 = Road;

                    }
                }
                updatingAdd = "K-Spot/" + Road + "/";
            } else if (K_Spot == -1) {
                if (K_Spot1 != K_Spot) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference(updatingAdd + konHTu + "/Location");
                    myRef.removeValue();
                    K_Spot1 = K_Spot;
                }
                updatingAdd = "database1/";
            }


            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference(updatingAdd + konHTu + "/Location");
            myRef.setValue(Loc);

        }
        else{
            Toast.makeText(getBaseContext(),
                    "Please provide location access",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void displayLocation(String Value) {
        TextView ValueView = (TextView) findViewById(lol);
        ValueView.setText(String.valueOf(Value));
    }
    private class MyLocationListener implements LocationListener
    {
        @Override
        public void onLocationChanged(Location loc) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onStatusChanged(String provider, int status,
                                    Bundle extras) {
            // TODO Auto-generated method stub
        }
    }
    public void bandKaro(View view){
        FirebaseDatabase database3 = FirebaseDatabase.getInstance();
        DatabaseReference myRef3 = database3.getReference(updatingAdd+konHTu);
        myRef3.removeValue();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.runFinalizersOnExit(true);
        System.exit(0);
        android.os.Process.killProcess(android.os.Process.myPid());

    }
    public void Police2(View view){
        FirebaseDatabase database3 = FirebaseDatabase.getInstance();
        DatabaseReference myRef3 = database3.getReference(updatingAdd+konHTu);
        myRef3.removeValue();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        st=0;
        startActivity(new Intent(getApplicationContext(), policeWith.class));


    }
}
