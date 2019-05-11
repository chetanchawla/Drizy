package com.example.divyansh.drizy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Divyansh on 15-08-2017.
 */

public class signInLoad extends Activity {
    static int signInPairs;
    static String pairs[][];
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_login);

        String naam2 = "copairs";
        DatabaseReference myRef2 = database.getReference(naam2);


        myRef2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.getValue() != null) {
                        //displayLocation("in first listener");
                        signInPairs = (int) dataSnapshot.getChildrenCount();
                        pairs = new String[signInPairs][2];
                        int i = 0;
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            pairs[i][0] = postSnapshot.getKey();
                            pairs[i][1] = postSnapshot.getValue(String.class);
                            i++;
                        }

                        startActivity(new Intent(getApplicationContext(), signIn.class));
                        finish();

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
public static String[][] returnPairs(){
    return pairs;
}
public static int returnSize(){
    return signInPairs;
}
}
