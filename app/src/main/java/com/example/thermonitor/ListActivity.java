package com.example.thermonitor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
ListView listView;

  // ArrayAdapter adapter;
    String[] strings={"Android","iPhone", "Windows","Blackberry","Linux"};
    int[]images={R.drawable.andr,R.drawable.apple,R.drawable.windows,R.drawable.blackberry,R.drawable.linux};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView=(ListView)findViewById(R.id.ListView);
      CustomAdapter customAdapter=new CustomAdapter();
        //adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,strings);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Intent intent=new Intent();
               if(position==0||position==1||position==2||position==3||position==4||position==5){
                 intent.setClass(ListActivity.this,DeviceDetailActivity.class);
                   startActivity(intent);
               }
            }
        });



    }
   class CustomAdapter extends BaseAdapter{
       @Override
       public int getCount() {
           return images.length;
       }

       @Override
       public Object getItem(int position) {
           return null;
       }

       @Override
       public long getItemId(int position) {
           return 0;
       }

       @Override
       public View getView(int position, View convertView, ViewGroup parent) {
           convertView=getLayoutInflater().inflate(R.layout.item,null);
           ImageView imageView=(ImageView) convertView.findViewById(R.id.imageView);
           TextView textView=(TextView) convertView.findViewById(R.id.textView);
           imageView.setImageResource(images[position]);
           textView.setText(strings[position]);



       return convertView;

       }
   }




}
