package com.example.sushma.vegetablemart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import com.example.sushma.vegetablemart.Adapters.SubVendorAdapter;
import com.example.sushma.vegetablemart.Databases.CartDatabase;
import com.example.sushma.vegetablemart.Databases.OrdersDatabase;
import com.example.sushma.vegetablemart.Pozos.CartPozo;

import java.util.ArrayList;
import java.util.List;

public class VendorActivity extends AppCompatActivity {

    ListView lv;
    CartDatabase database;

    List<CartPozo> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor);

        lv=findViewById(R.id.listView3);
        database=new CartDatabase(this);
        list=database.getVendorDetails();
        SubVendorAdapter vendorAdapter=new SubVendorAdapter(this,list);
        lv.setAdapter(vendorAdapter);
    }

    public void back(View view) {
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
