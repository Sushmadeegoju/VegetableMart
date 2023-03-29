package com.example.sushma.vegetablemart;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sushma.vegetablemart.Adapters.CartAdapter;
import com.example.sushma.vegetablemart.Databases.CartDatabase;
import com.example.sushma.vegetablemart.Databases.OrdersDatabase;
import com.example.sushma.vegetablemart.Pozos.CartPozo;

import java.util.List;



public class CartActivity extends AppCompatActivity {
    public static ListView lv;
    CartDatabase db;
    TextView tv;
    CartPozo pozo;
    public static CartAdapter adapter;
    OrdersDatabase ordersDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        lv=findViewById(R.id.listView2);
        db=new CartDatabase(this);
        List<CartPozo> list=db.getData();
        tv=findViewById(R.id.textView33);
        adapter=new CartAdapter(this,list);
        lv.setAdapter(adapter);
        tv.setText(""+db.getTotalAmount());
        ordersDatabase=new OrdersDatabase(this);
    }

    public void clearAll(View view) {
        db.deleteAll();
        lv.setAdapter(adapter);
        Toast.makeText(this,"data cleared successfully",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(this,CartActivity.class);
        startActivity(intent);
    }

    public void ConfirmOrder(View view) {
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Thank You").setMessage("Your Order has been placed successfully").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                builder.setCancelable(true);
            }
        }).setCancelable(false).show();
       String amount= tv.getText().toString();
        ordersDatabase.insertData(amount);
        Intent intent=new Intent(this,MyOrdersActivity.class);
        startActivity(intent);

    }
}
