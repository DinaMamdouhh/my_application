package com.example.thermonitor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity implements View.OnClickListener{
    private EditText name;
    private EditText confirm;
    private Button Signin;
    private EditText password;
    private EditText username;
    private EditText email;
    int id;
    private static final String PREFS_NAME ="LginPrefs";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText) findViewById(R.id.editTextname);
        password = (EditText) findViewById(R.id.editTextpasswordd);
        Signin = (Button) findViewById(R.id.signin);
        confirm = (EditText) findViewById(R.id.editTextconfrim);
        username = (EditText) findViewById(R.id.editTexttusername);
        email = (EditText) findViewById(R.id.editTextemail);
        Signin.setOnClickListener(this);


        }



    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.signin: {
                if(name.getText().toString().equals("")||password.getText().toString().equals("")||confirm.getText().toString().equals("")||username.getText().toString().equals("")||email.getText().toString().equals("")){
                    Toast.makeText(this, "ENTER THE MISSING REQUIREMENTS", Toast.LENGTH_SHORT).show();
                }

                else if(!(password.getText().toString().equals(confirm.getText().toString()))){
                        Toast.makeText(this, "PASSWORDS DOESN'T MATCH!!", Toast.LENGTH_SHORT).show();
                    }



                 else{
                    Toast.makeText(this, "SIGNIN BUTTON IS CLICKED", Toast.LENGTH_SHORT).show();
                    Intent intent3 = new Intent(this, ListActivity.class);
                    startActivity(intent3);
                }

            }
    }



}}
