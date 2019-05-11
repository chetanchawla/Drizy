
package com.example.divyansh.drizy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by HP on 7/20/2017.
 */

public class policeWith extends Activity {
    private LocationManager lm;                 //sets a ocation manager named lm
    private LocationListener locationListener;
    public location Loc = new location();
    public String namIs;
    public static int x=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.police_layout);
        namIs=checkLogIn.getMac();
    }

    public void Yes(View view) {
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
            Loc.setLat(loc.getLatitude());
            Loc.setAlt(loc.getAltitude());
            Loc.setLong(loc.getLongitude());
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Police-Notification/"+namIs+"/Location");
            myRef.setValue(Loc);
        } else {
            Toast.makeText(getBaseContext(),
                    "Location is null",
                    Toast.LENGTH_SHORT).show();
        }
        startActivity(new Intent(getApplicationContext(), Approaching.class));
        finish();
    }
    public void No(View view){
        startActivity(new Intent(getApplicationContext(), WannaSignIn.class));
        finish();
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
}

