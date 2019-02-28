package com.example.thermonitor;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Register extends AppCompatActivity implements View.OnClickListener{
    private EditText name;
    private EditText confirm;
    private Button Signin;
    private EditText password;
    private EditText username;
    private EditText email;
    int id;
    private static final String PREFS_NAME ="LginPrefs";
    private FirebaseAuth firebaseAuth;
    

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText) findViewById(R.id.editTextname);
        password = (EditText) findViewById(R.id.editTextpassword);
        Signin = (Button) findViewById(R.id.signin);
        confirm = (EditText) findViewById(R.id.editTextconfirm);
        username = (EditText) findViewById(R.id.editTextusername);
        email=(EditText)findViewById(R.id.editTextemail);
        firebaseAuth=FirebaseAuth.getInstance();
        Signin.setOnClickListener(this);


        }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.signin: {
                final ProgressDialog progressDialog=ProgressDialog.show(Register.this,"PLEASE WAIT ........","PROCESSING ........",true);
                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (name.getText().toString().equals("") || password.getText().toString().equals("") || confirm.getText().toString().equals("") || username.getText().toString().equals("") || email.getText().toString().equals("")) {
                            Toast.makeText(Register.this, "ENTER THE MISSING REQUIREMENTS", Toast.LENGTH_SHORT).show();

                        } else if (!(password.getText().toString().equals(confirm.getText().toString()))) {
                            Toast.makeText(Register.this, "PASSWORDS DOESN'T MATCH!!", Toast.LENGTH_SHORT).show();
                        } else if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "SIGNIN BUTTON IS CLICKED", Toast.LENGTH_SHORT).show();
                            Intent intent3 = new Intent(Register.this, DeviceListActivity.class);
                            startActivity(intent3);
                        } else {
                            Log.d("ERROR", task.getException().toString());
                            Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();


                        }
                    }

        });


            }}

   /* private Boolean validate() {
        Boolean result = false;
        String usrname = username.getText().toString();
        String pass = password.getText().toString();
        String namee = name.getText().toString();
        String confrimm = confirm.getText().toString();
        String emails = email.getText().toString();
        if (usrname.isEmpty() || pass.isEmpty() || namee.isEmpty() || confrimm.isEmpty()) {
           Toast.makeText(this,"ENTER THE MISSING REQUIREMENTS", Toast.LENGTH_SHORT).show();
        }
        else if(!(password.getText().toString().equals(confirm.getText().toString()))){
            Toast.makeText(this, "PASSWORDS DOESN'T MATCH!!", Toast.LENGTH_SHORT).show();
        }
        else {
            result= true;
        }
        return  result;
    }*/


    }}









