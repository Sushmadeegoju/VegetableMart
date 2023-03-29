package com.example.sushma.vegetablemart.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sushma.vegetablemart.CartActivity;
import com.example.sushma.vegetablemart.Databases.CartDatabase;
import com.example.sushma.vegetablemart.Pozos.CartPozo;
import com.example.sushma.vegetablemart.R;

import java.util.List;

import static com.example.sushma.vegetablemart.CartActivity.adapter;
import static com.example.sushma.vegetablemart.CartActivity.lv;

public class CartAdapter extends BaseAdapter {
    Context context;
    List<CartPozo> list;
    CartPozo pozo;
    CartDatabase db;
    public CartAdapter(CartActivity context, List<CartPozo> list) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.cart_layout,null);
        final TextView tv1,tv2,tv3,tv4;
        ImageView iv=convertView.findViewById(R.id.imageView2);
        tv1=convertView.findViewById(R.id.textView12);
        tv2=convertView.findViewById(R.id.textView14);
        tv3=convertView.findViewById(R.id.textView15);
        tv4=convertView.findViewById(R.id.textView16);
        pozo=list.get(position);
        tv1.setText(""+pozo.getAmount());//price for 1kg
        tv2.setText(pozo.getQuantity());
        tv3.setText(pozo.getCost());//total cost for one item
        tv4.setText(pozo.getName());
        iv.setImageResource(pozo.getImage());
        db=new CartDatabase(context);
        Button btn=convertView.findViewById(R.id.button7);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteSingleRecord(tv4.getText().toString());
                Toast.makeText(context,"item deleted",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(context,CartActivity.class);
                context.startActivity(intent);
                /*Intent intent=new Intent(context,CartActivity.class);
                context.startActivity(intent);*/
            }
        });
        return convertView;
    }
}
