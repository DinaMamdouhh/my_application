package com.example.thermonitor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class CustomAdapter extends ArrayAdapter<String> {
    public CustomAdapter(Context context, ArrayList<String> wifinames) {
        super(context,R.layout.row, wifinames);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View CustomView = inflater.inflate(R.layout.row,parent,false);
        String item= getItem(position);
        TextView text = CustomView.findViewById(R.id.textViewdevicelist);
        ImageView imageview = CustomView.findViewById(R.id.imageViewdevicelist);
        text.setText(item);
        imageview.setImageResource(R.drawable.dinaimage);


        return CustomView;
    }
}
