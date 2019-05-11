package com.example.divyansh.drizy;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public TextToSpeech t1;
    private  int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION;
    private int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        TextView tx = (TextView)findViewById(R.id.textview1);
//        Intent tetherSettings = new Intent();
//        tetherSettings.setClassName("com.android.settings","com.android.settings.TetherSettings");
//        startActivity(tetherSettings);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Montel"+
                ".ttf");

        tx.setTypeface(custom_font);
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
        if (ContextCompat.checkSelfPermission( this , android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
        }



    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            // permission was granted, yay! Do the
            // contacts-related task you need to do.

        } else {
            t1.speak("Please give permissions", TextToSpeech.QUEUE_FLUSH, null);
            while (t1.isSpeaking()) {

            }
            super.onDestroy();
        }
        return;
    }


    public void System(View view){
        //systemHai=1;
        startActivity(new Intent(getApplicationContext(), checkLogIn.class));


    }
    public void noSystem(View view){
        //systemHai=0;
        startActivity(new Intent(getApplicationContext(), intermediate.class));

    }
    public void AboutUs(View view){
        startActivity(new Intent(getApplicationContext(), about.class));
    }
    public void howTo(View view)
    {
        startActivity(new Intent(getApplicationContext(), How.class));
    }

}
