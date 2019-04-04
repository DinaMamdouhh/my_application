package com.example.thermonitor;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DeviceDetailActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private DatabaseReference myRef;
    private TextView joly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        joly = findViewById(R.id.joly);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_detail);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("thermometerr-8de82");
        Query query = reference.child("Temprature");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String temp = dataSnapshot.getValue().toString();
                    Log.d("FAIL", temp);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("FAIL", databaseError.getMessage());
            }
        });

        Log.d("FAIL", "onCreate: ");
    }
}