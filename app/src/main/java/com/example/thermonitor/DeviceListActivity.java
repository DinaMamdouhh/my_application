package com.example.thermonitor;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.Manifest;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

public class DeviceListActivity extends Activity implements View.OnClickListener {
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    TextView txt;
    WifiManager Wifi;
    WifiReceiver receiverWifi;
    List<ScanResult> wifiList;
    ArrayList<String> wifinames = new ArrayList<>();
    StringBuilder stringbuilder = new StringBuilder();
    StringBuilder csv = new StringBuilder();
    boolean scanfinish = false;
    ListView lv;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 87);
        }
        txt = findViewById(R.id.texttttttt);
        lv = findViewById(R.id.list);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), wifinames);
        lv.setAdapter(customAdapter);
        Wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (Wifi.isWifiEnabled() == false) {
            Toast.makeText(getApplicationContext(), "Wifi is Disabled......making it enabled", Toast.LENGTH_LONG).show();
            Wifi.setWifiEnabled(true);
        }

        receiverWifi = new WifiReceiver();
        registerReceiver(receiverWifi, new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedESP = String.valueOf(parent.getItemAtPosition(position));
                String mac = onlyMacString(selectedESP);
                Intent intent = new Intent();
                intent.putExtra("ESPMAC", mac);
                Log.d("MACTST", "onItemClick: " + mac);
                intent.setClass(DeviceListActivity.this, DeviceDetailActivity.class);
                startActivity(intent);
            }
        });
        Wifi.startScan();
        txt.setText("Start Scanning..........");
    }

    private String onlyMacString(String str) {
        String res = "";
        str = str.toUpperCase();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'M') {
                i++;
                if (str.charAt(i) == 'A') {
                    i++;
                    if (str.charAt(i) == 'C') {
                        i++;
                        if (str.charAt(i) == ':') {
                            i += 1;
                            int t = i;
                            for (; i < str.length(); i++) {
                                if (i == t + 1)
                                    res = res + (char) ((int) str.charAt(i) - 2);
                                else {
                                    res = res + (str.charAt(i));

                                }
                            }
                            System.out.println("Done: " + res);
                            break;
                        }
                    }
                }
            }
        }
        return res;
    }



    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiverWifi);

        Intent scanResults = new Intent();
        scanResults.putExtra("AP_LIST", csv.toString());
        setResult(RESULT_OK, scanResults);
        finish();
    }

    protected void onResume() {
        super.onResume();

        registerReceiver(receiverWifi, new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    class WifiReceiver extends BroadcastReceiver {
        public void onReceive(Context c, Intent intent) {
            stringbuilder = new StringBuilder();
            csv = new StringBuilder();
            wifiList = Wifi.getScanResults();

            for (int i = 0; i < wifiList.size(); i++) {
                if (wifiList.get(i).SSID.equalsIgnoreCase("espap"))
                    wifinames.add("SSID: " + wifiList.get(i).SSID + "\n" + " MAC:" + wifiList.get(i).BSSID.toUpperCase());


            }


            txt.setText(stringbuilder);
            CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), wifinames);
            lv.setAdapter(customAdapter);
            scanfinish = true;
        }
    }


}