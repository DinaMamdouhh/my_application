package com.example.thermonitor;
import android.Manifest;
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
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DeviceListActivity extends Activity implements View.OnClickListener {
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

        txt = findViewById(R.id.texttttttt);
        lv =findViewById(R.id.list);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), wifinames);
        lv.setAdapter(customAdapter);
        Wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        receiverWifi = new WifiReceiver();
        registerReceiver(receiverWifi, new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.setClass(DeviceListActivity.this,DeviceDetailActivity.class);
                startActivity(intent);
            }
        });
        Wifi.startScan();
        txt.setText("Start Scanning..........");
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
                if(wifiList.get(i).SSID.equalsIgnoreCase("espap"))
                    wifinames.add("SSID: " + wifiList.get(i).SSID + "\n" + " Mac Address:" + wifiList.get(i).BSSID);


            }

            txt.setText(stringbuilder);
            CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), wifinames);
            lv.setAdapter(customAdapter);
            scanfinish = true;
        }
    }


}