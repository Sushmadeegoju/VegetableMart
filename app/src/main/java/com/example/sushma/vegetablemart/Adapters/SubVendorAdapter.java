package com.example.sushma.vegetablemart.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.sushma.vegetablemart.Databases.CartDatabase;
import com.example.sushma.vegetablemart.Pozos.CartPozo;
import com.example.sushma.vegetablemart.R;
import com.example.sushma.vegetablemart.VendorActivity;

import java.util.List;

public class SubVendorAdapter extends BaseAdapter {
    Context context;
    List<CartPozo> list;
    CartPozo pozo;
    CartDatabase database;
    public SubVendorAdapter(Context context, List<CartPozo> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.subvendorcustom,null);
        final TextView tv1,tv2,tv3,tv4;
        tv1=convertView.findViewById(R.id.textView37);
        tv2=convertView.findViewById(R.id.textView43);
        tv3=convertView.findViewById(R.id.textView45);
        tv4=convertView.findViewById(R.id.textView46);
        Button b1=convertView.findViewById(R.id.button15);
        pozo=list.get(position);
        tv1.setText(pozo.getMobile());
        tv2.setText(pozo.getName());
        tv3.setText(pozo.getQuantity());
        tv4.setText(pozo.getCost());
        database=new CartDatabase(context);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.deleteSingleVendor(tv1.getText().toString(),tv2.getText().toString());
                Intent intent=new Intent(context, VendorActivity.class);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
