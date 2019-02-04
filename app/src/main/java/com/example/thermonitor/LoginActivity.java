package com.example.thermonitor;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText Username;
    private EditText Password;
    private Button Signup;
    private Button Login;
    private int Counter=5;
    private CheckBox checkboxrememberme;
    private SharedPreferences mprefs;
    private static final String PREFS_NAME ="LginPrefs";
    private SharedPreferences.Editor meditor;
    private  static final String TAG="LoginActivity";
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

      // mprefs= PreferenceManager.getDefaultSharedPreferences(this);
        //meditor=mprefs.edit();
       //checkSharedpref();
        Username = (EditText) findViewById(R.id.editTextusername);
        Password = (EditText) findViewById(R.id.editTextpassword);
        Signup = (Button) findViewById(R.id.signup);
        Login = (Button) findViewById(R.id.login);
        Signup.setOnClickListener(this);
        Login.setOnClickListener(this);
        checkboxrememberme=(CheckBox)findViewById(R.id.checkBoxrememberme);


    }
/*private void checkSharedpref(){
 String checkbox=mprefs.getString(getString(R.string.checkbox),"false");
 String namee=mprefs.getString(getString(R.string.name)," ");
 String passs=mprefs.getString(getString(R.string.password),"");

Username.setText(namee);
Password.setText(passs);

if(checkbox.equals("True")){
    checkboxrememberme.setChecked(true);
}
else
{
    checkboxrememberme.setChecked(false);
}}

*/





   /* public void getPreferencesData(){
        SharedPreferences sp=getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        if(sp.contains("pref_name")){
            String u=sp.getString("pref_name","not found");
            Username.setText(u.toString());
        }
        if(sp.contains("pref_pass")){
            String p=sp.getString("pref_pass","not found");
            Password.setText(p.toString());
        }

        if(sp.contains("pref_check")){
            Boolean b=sp.getBoolean("pref_check",false);
            checkboxrememberme.setChecked(b);
        }}
*/






    @Override
    public void onClick(View v) {
     /* if(checkboxrememberme.isChecked()){
Boolean booleanchecked=checkboxrememberme.isChecked();
SharedPreferences.Editor editor=mprefs.edit();
editor.putString("pref_name",Username.getText().toString());
editor.putString("pref_pass",Password.getText().toString());
editor.putBoolean("pref_check",booleanchecked);
editor.apply();
          Toast.makeText(getApplicationContext(), "SETTINGS HAVE BEEN SAVED", Toast.LENGTH_SHORT).show();
//Toast.makeText(this, "SAVED", Toast.LENGTH_SHORT).show();
      }
      else{
          mprefs.edit().clear().apply();
      }
*/
    /* if(checkboxrememberme.isChecked()){
         meditor.putString(getString(R.string.checkbox),"True");
         meditor.commit();

         String namef=Username.getText().toString();
         meditor.putString(getString(R.string.Username),namef);
         meditor.commit();


         String passf=Password.getText().toString();
         meditor.putString(getString(R.string.Password),passf);
         meditor.commit();
     }
 else{
         meditor.putString(getString(R.string.checkbox),"False");
         meditor.commit();

         meditor.putString(getString(R.string.Username),"");
         meditor.commit();

         meditor.putString(getString(R.string.Password),"");
         meditor.commit();

     }*/




        if (v.getId() == R.id.login) {
            if (Username.getText().toString().equals("Dina") && Password.getText().toString().equals("1234")) {
                Toast.makeText(this, "LOGIN BUTTON IS CLICKED", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(this, ListActivity.class);
                startActivity(intent1);
                   Username.getText().clear();
                   Password.getText().clear();

            }
            else if(Username.getText().toString().equals("") && Password.getText().toString().equals("")){

                Toast.makeText(this, "PLEASE ENTER USERNAME AND PASSWORD", Toast.LENGTH_SHORT).show();

            }

            else{
            if(Counter!=0){
                Toast.makeText(this,"USERNAME AND PASSWORD DOESNOT MATCH PLEASE TRY AGAIN!!",Toast.LENGTH_SHORT).show();
            } else if(Counter==0){
                Login.setEnabled(false);
                    Toast.makeText(this,"ERROR:PLEASE SIGNUP",Toast.LENGTH_SHORT).show();
                }

        } }
        else {
            if (v.getId() == R.id.signup) {

                Toast.makeText(this, "SIGNUP BUTTON IS CLICKED", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(this, Register.class);
                startActivity(intent2);


            }

        }
   Counter--;

    }

}
