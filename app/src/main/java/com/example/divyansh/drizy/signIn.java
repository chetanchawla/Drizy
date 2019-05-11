package com.example.divyansh.drizy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by Divyansh on 16-08-2017.
 */

public class signIn extends Activity {

    EditText ssidUser;
    EditText passUser;

    //TextView ssidMess;
    //TextView passMess;
    static String ssid;
    static String pass;
    static String ssid2;
    static String pass2;
    String[][] pairs;
    int noPairs;
    static upload obj = new upload();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        ssidUser = (EditText)findViewById(R.id.ssidView);
        passUser = (EditText)findViewById(R.id.passView);
        //ssidMess = (TextView) findViewById(R.id.ssidMess);
        //passMess = (TextView) findViewById(R.id.passMess);
        Toast.makeText(this,"reached signin",Toast.LENGTH_LONG).show();
        pairs=signInLoad.returnPairs();
        noPairs = signInLoad.returnSize();

    }
   public void login(View view)
    {

        //passMess.setText("");
        //ssidMess.setText("");
        //int check=0;
        ssid = ssidUser.getText().toString();
        pass = passUser.getText().toString();
        for(int i=0;i<noPairs;i++){
            if(pairs[i][0].equals(ssid)){
                if(pairs[i][1].equals(pass)){
//

                    obj.set(ssid,pass);
                    ssid2=ssid;
                    pass2=pass;
//                    FirebaseDatabase database = FirebaseDatabase.getInstance();
//                    String name="Device Log-In/"+checkLogIn.getMac();
//                    DatabaseReference myRef3 = database.getReference(name);
//                    myRef3.setValue(obj);
                    startActivity(new Intent(getApplicationContext(), load.class));
                    finish();
                }
                else
                {
                   Toast.makeText(this,"Wrong Password",Toast.LENGTH_LONG).show();
                    // passMess.setText("Wrong password");
                }

            break;
            }

        else
            {
                if(i==noPairs-1)
                {
                    Toast.makeText(this,"Wrong ssid",Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(getApplicationContext(), signIn.class));
//                    finish();
                             }
            }
        }


    }
    static public String getSSID()
    {
        return ssid2;
    }
    static public String getPass()
    {
        return pass2;
    }
    static public upload getObj()
    {
        return obj;
    }


}
