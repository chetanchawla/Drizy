package com.example.divyansh.drizy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Divyansh on 29-08-2017.
 */

public class WannaSignIn extends Activity {
    String macid=checkLogIn.getMac();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String naam= "Device Log-In/"+macid;
    DatabaseReference myRef = database.getReference(naam);
    Button continueS;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wanna_sign_in);
        continueS=(Button) findViewById(R.id.continueS);
        continueS.setText("Continue with "+ checkLogIn.returnSSID());

    }
    public void moveOn(View view)
    {
        startActivity(new Intent(getApplicationContext(), Third.class));
        finish();
    }
    public void delete(View view)
    {
        myRef.removeValue();
        startActivity(new Intent(getApplicationContext(), checkLogIn.class));
        finish();

    }
}
