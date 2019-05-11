package com.example.divyansh.drizy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Divyansh on 26-08-2017.
 */

public class load extends Activity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String name="Device Log-In/"+checkLogIn.getMac();
    DatabaseReference myRef3 = database.getReference(name);
    public static upload obj2 =signIn.getObj();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_login);

        myRef3.setValue(obj2);

        startActivity(new Intent(getApplicationContext(), Third.class));
        finish();
    }
    static public upload getObj2()
    {
        return(obj2);
    }

}
