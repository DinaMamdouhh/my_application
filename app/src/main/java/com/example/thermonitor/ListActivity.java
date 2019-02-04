package com.example.thermonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {
ListView listView;
    ArrayAdapter adapter;
    String[] strings={"Android","iPhone", "Windows","Bluckberry","Linux"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView=(ListView)findViewById(R.id.ListView);
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,strings);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               if(position==0||position==1||position==2||position==3||position==4||position==5){
                   Intent intent1=new Intent(view.getContext(),DeviceDetailActivity.class);
                   startActivity(intent1);
               }
            }
        });


    }
}
