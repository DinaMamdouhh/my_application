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
    private String dataPath = "Temprature";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_detail);
        joly =findViewById(R.id.joly);
        Bundle bundle = getIntent().getExtras();

        if(bundle.getString("ESPMAC")!=null){
            dataPath = bundle.getString("ESPMAC");
        }
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(dataPath);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Long s= dataSnapshot.getValue(Long.class);
                joly.setText("TEMPRATURE HERE IS: "+s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                joly.setText(databaseError.getMessage());
            }
        });

    }
}