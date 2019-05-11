package com.example.divyansh.drizy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.Ringtone;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

import static android.os.Process.myPid;
import static com.example.divyansh.drizy.R.id.lol;


public class Second extends Activity {
    private String[] val = new String[100];
    private LocationManager lm;                 //sets a ocation manager named lm
    private LocationListener locationListener;  //sets a location listener named locationListener
    //public String MACaddr; //used to save the MAC address
    private String don;
    public int Road = -1;
    public int Road1 = Road;
    public int K_Spot = -1;
    public int K_Spot1 = K_Spot;
    public float x2, x1, y2, y1;
    public static String toing;
    public int Direction = -1;
    public int countt = -1;
    public double extx2 = -1;
    public double extx1 = -1;
    public double exty1 = -1;
    public double exty2 = -1;
    public int HaiPrev = -1;
    public double prevLat = -1;
    public double prevLong = -1;
    public int st=1;
    public String pickUp;
    public int[] returnInsideThing = new int[3];
    public String updatingAdd = "database1/";
    public hmm hotspot = new hmm();
    public TextToSpeech t1;
    EditText mEdit;
    public location Loc = new location();
    public String uthaoMess;
    public Uri notification;
    public Ringtone r;
    public int speak = 1;
    public int abNaBol=1;
    public String overSpeed="";
    public int pBar=0;
    public final int duration = 2; // seconds
    public final int sampleRate = 8000;
    public final int numSamples = duration * sampleRate;
    public final double sample[] = new double[numSamples];
    public final double freqOfTone = 880; // hz
    public final byte generatedSnd[] = new byte[2 * numSamples];
    public final byte generatedSnd2[] = new byte[2 * numSamples];
    public ProgressBar mProgressBar;
    public TextView textIs;
    //ProgressBar mProgressBar=  (ProgressBar) findViewById(R.id.pr);
    //ProgressDialog mProgressBar = new ProgressDialog(Second.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mProgressBar=  (ProgressBar) findViewById(R.id.pr);
        textIs=(TextView)findViewById(R.id.udao);
        genTone();
        genTone2();
        toing = intermediate.returnID();

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
//        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        pickUp = "Alerts/" + toing + "/";
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        final DatabaseReference makise = database1.getReference(pickUp);
        makise.addValueEventListener(new ValueEventListener() {

            public static final String TAG = "VV";

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int i = 0;

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    val[i] = postSnapshot.getValue(String.class);
                    i++;
                }
                don = val[0];
                if(val[1]!=""&&val[1]!=null){
                    Toast.makeText(getBaseContext(),
                            val[1],
                            Toast.LENGTH_SHORT).show();
                    if(speak==1) {
                        t1.speak(val[1], TextToSpeech.QUEUE_FLUSH, null);
                        while (t1.isSpeaking()) {

                        }
                    }
                    else if(speak==0&&val[1].equals("Alert"))
                    {
                        playSound();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    else if(speak==0&&val[1].equals("OverSpeeding")){
                        playSound2();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
                if(val[2]!=""&&val[2]!=null){
                    Toast.makeText(getBaseContext(),
                            val[2],
                            Toast.LENGTH_SHORT).show();
                    if(speak==1) {
                        t1.speak(val[2], TextToSpeech.QUEUE_FLUSH, null);
                        while (t1.isSpeaking()) {
                        }
                    }
                    else if(speak==0&&val[1].equals("Alert"))
                    {
                        playSound();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    else if(speak==0&&val[1].equals("OverSpeeding")){
                        playSound2();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    }

                }
                val[1]="";
                val[2]="";


                                /*for (int j = 0; j < i; j++) {
                                    don = don + val[j];
                                }*/

                if (don != null && don != ""&&don !="OverSpeeding"&&don!="Ambulance") {
                    try {
                        //r.play();

                        if (abNaBol % 2 != 0) {
                            how();
                        }
                        abNaBol++;
                        Thread.sleep(500);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    displayLocation(don);
                    countt=0;

                }
                don="";
                makise.removeValue();
            }



            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
                Toast.makeText(getBaseContext(),
                        "unable to connect to database, can't read message",
                        Toast.LENGTH_SHORT).show();
            }

        });

        chalao();

    }

    @Override
    protected void onDestroy() {

        FirebaseDatabase database3 = FirebaseDatabase.getInstance();
        DatabaseReference myRef3 = database3.getReference(updatingAdd + toing);
        myRef3.removeValue();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finish();
        super.onDestroy();
        System.runFinalizersOnExit(true);
        System.exit(0);
        android.os.Process.killProcess(myPid());

    }

    public void chalao() {
        final Handler h = new Handler();
        final int delay = 1000; //milliseconds
        h.postDelayed(new Runnable() {
            public void run() {
                if(st!=0) {
                    chalio();
                    pBar++;
                    if(pBar==4){
                        mProgressBar.setVisibility(View.INVISIBLE);
                        textIs.setVisibility(View.INVISIBLE);

                    }
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
                    DatabaseReference myRef = database.getReference(updatingAdd + toing + "/Location");
                    myRef.removeValue();
                    K_Spot1 = K_Spot;
                }
                else{
                    if(Road1!= Road){
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference(updatingAdd + toing + "/Location");
                        myRef.removeValue();
                        Road1 = Road;

                    }
                }
                updatingAdd = "K-Spot/" + Road + "/";
            } else if (K_Spot == -1) {
                if (K_Spot1 != K_Spot) {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference(updatingAdd + toing + "/Location");
                    myRef.removeValue();
                    K_Spot1 = K_Spot;
                }
                updatingAdd = "database1/";
            }


            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference(updatingAdd + toing + "/Location");
            myRef.setValue(Loc);

            if(countt%4==0){
                displayLocation("");
                countt=-1;
            }
            else if(countt!=-1){
                countt++;
            }
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
    public void how(){
        if(speak == 0&&!don.equals("Ambulance")){
            //r.play();
            if(don.equals("Alert")){
            playSound();}
            else if(don.equals("OverSpeeding")){
                playSound2();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else
        {
            t1.speak(don,TextToSpeech.QUEUE_FLUSH, null);
            while (t1.isSpeaking()){
                displayLocation(don);
            }
        }
    }
    public void letsChange(View view){
        if(speak ==0){
            speak = 1;

        }
        else
        {
            t1.speak("Collision Alert tone is",TextToSpeech.QUEUE_FLUSH, null);
            while (t1.isSpeaking()){
            }

            playSound();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t1.speak("Overspeeding warning tone is",TextToSpeech.QUEUE_FLUSH, null);
            while (t1.isSpeaking()){
            }
            playSound2();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            speak = 0;
        }
    }
    public void bandKaro(View view){
        //ekBool=1;
        FirebaseDatabase database3 = FirebaseDatabase.getInstance();
        DatabaseReference myRef3 = database3.getReference(updatingAdd+toing);
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
    public void genTone(){
        // fill out the array
        for (int i = 0; i < numSamples; ++i) {
            sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/freqOfTone));
        }

        // convert to 16 bit pcm sound array
        // assumes the sample buffer is normalised.
        int idx = 0;
        for (double dVal : sample) {
            short val = (short) (dVal * 32767);
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);
        }
    }
    public void genTone2(){
        // fill out the array
        for (int i = 0; i < numSamples; ++i) {
            sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/(freqOfTone/2)));
        }

        // convert to 16 bit pcm sound array
        // assumes the sample buffer is normalised.
        int idx = 0;
        for (double dVal : sample) {
            short val = (short) (dVal * 32767);
            generatedSnd2[idx++] = (byte) (val & 0x00ff);
            generatedSnd2[idx++] = (byte) ((val & 0xff00) >>> 8);
        }
    }

    void playSound(){
        AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                8000, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                AudioFormat.ENCODING_PCM_16BIT, numSamples,
                AudioTrack.MODE_STATIC);
        audioTrack.write(generatedSnd, 0, numSamples);
        audioTrack.play();
    }
    void playSound2(){
        AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                8000, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                AudioFormat.ENCODING_PCM_16BIT, numSamples,
                AudioTrack.MODE_STATIC);
        audioTrack.write(generatedSnd2, 0, numSamples);
        audioTrack.play();
    }
    public void Police(View view){
        FirebaseDatabase database3 = FirebaseDatabase.getInstance();
        DatabaseReference myRef3 = database3.getReference(updatingAdd+toing);
        myRef3.removeValue();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        st=0;
        startActivity(new Intent(getApplicationContext(), police.class));

    }
    public static String toingName(){
        return toing;
    }
}

