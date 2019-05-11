package com.example.divyansh.drizy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by HP on 7/21/2017.
 */

public class Approaching extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.approach);
    }
    public void quit(View view){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
