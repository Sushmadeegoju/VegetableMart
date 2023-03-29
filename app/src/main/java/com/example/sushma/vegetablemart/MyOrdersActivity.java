package com.example.sushma.vegetablemart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sushma.vegetablemart.Adapters.OrdersAdapter;
import com.example.sushma.vegetablemart.Databases.OrdersDatabase;
import com.example.sushma.vegetablemart.Pozos.OrdersPojo;

import java.util.List;

public class MyOrdersActivity extends AppCompatActivity {
    RecyclerView rv;
    List<OrdersPojo> list;
    ListView lv;
    OrdersDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        rv=findViewById(R.id.recyclerView4);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
       lv=findViewById(R.id.listView2);
        db=new OrdersDatabase(this);
        list=db.getData();
        OrdersAdapter ordersAdapter=new OrdersAdapter(this,list);
        rv.setAdapter(ordersAdapter);
       }

    public void back(View view) {
        Intent intent=new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    public void clearAll(View view) {
        db.deleteAll();
        Intent intent=new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
}
