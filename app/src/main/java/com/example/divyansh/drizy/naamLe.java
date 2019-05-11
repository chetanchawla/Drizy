package com.example.divyansh.drizy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

/**
 * Created by HP on 7/13/2017.
 */

public class naamLe extends Activity {
    EditText mEdit;
    public static String yourName;
    public static String nameIs;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.naam);
        mEdit = (EditText)findViewById(R.id.DeathNote);
    }
    public void naamKaran(View view)
    {
        yourName= mEdit.getText().toString();
        //startActivity(new Intent(getApplicationContext(), Third.class));
    }
    public static String naamDe(){
        nameIs=yourName;
        return nameIs;
    }
}
