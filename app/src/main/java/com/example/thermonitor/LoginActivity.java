package com.example.thermonitor;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.HashMap;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText Emaillogin;
    private EditText Password;
    private Button Signup;
    private Button Login;
    private int Counter=5;
    private CheckBox checkboxrememberme;
    private SharedPreferences mprefs;
    private static final String PREFS_NAME ="LginPrefs";
    private SharedPreferences.Editor meditor;
    private  static final String TAG="LoginActivity";
    private TextView userRegistration;
    private FirebaseAuth firebaseAuth;

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Emaillogin = (EditText) findViewById(R.id.editTextemails);
        Password = (EditText) findViewById(R.id.editTextpassword);
        Signup = (Button) findViewById(R.id.signup);
        Login = (Button) findViewById(R.id.login);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();

        if(user!=null){
            finish();
            startActivity(new Intent(LoginActivity.this,DeviceListActivity.class));
        }

        Signup.setOnClickListener(this);
        Login.setOnClickListener(this);
        checkboxrememberme=(CheckBox)findViewById(R.id.checkBoxrememberme);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login) {
            final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, "PLEASE WAIT ........", "PROCESSING ........", true);
            firebaseAuth.signInWithEmailAndPassword(Emaillogin.getText().toString(), Password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if (!(Emaillogin.getText().toString().equals("") && Password.getText().toString().equals(""))) {
                        if(task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "LOGIN BUTTON IS CLICKED", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(LoginActivity.this, DeviceListActivity.class);
                            startActivity(intent1);
                            Emaillogin.getText().clear();
                            Password.getText().clear();
                        }
                        else{
                            Log.d("ERROR", task.getException().toString());
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            Counter--;
                            if (Counter == 0) {
                                Login.setEnabled(false);
                                Toast.makeText(LoginActivity.this, "ERROR:PLEASE SIGNUP", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    else if (Emaillogin.getText().toString().equals("") && Password.getText().toString().equals("")) {

                        Toast.makeText(LoginActivity.this, "PLEASE ENTER USERNAME AND PASSWORD", Toast.LENGTH_SHORT).show();

                    }

                }
            });


        } else {
            if (v.getId() == R.id.signup) {

                Toast.makeText(this, "SIGNUP BUTTON IS CLICKED", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(this, Register.class);
                startActivity(intent2);


            }

        }


    }}

