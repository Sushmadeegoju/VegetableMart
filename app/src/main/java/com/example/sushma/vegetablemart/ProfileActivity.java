package com.example.sushma.vegetablemart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sushma.vegetablemart.Databases.SignUpDatabase;
import com.example.sushma.vegetablemart.Pozos.SignUpPozo;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv4,tv5,tv6;
    SignUpDatabase db;
    List<SignUpPozo> list=new ArrayList<>();
    SignUpPozo pozo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tv1=findViewById(R.id.textView13);
        tv2=findViewById(R.id.textView17);
        tv3=findViewById(R.id.textView18);
        tv4=findViewById(R.id.textView19);
        tv5=findViewById(R.id.textView20);
        tv6=findViewById(R.id.textView21);
        db=new SignUpDatabase(this);
        list=db.getData();
        pozo=list.get(0);
        tv1.setText("Name: "+pozo.getFname()+" "+pozo.getLname());
        tv2.setText("Email: "+pozo.getEmail());
        tv3.setText("Mobile no.: "+pozo.getMobile());
        tv4.setText("City: "+pozo.getCity());
        tv5.setText("State: "+pozo.getState());
        tv6.setText("Pincode: "+pozo.getAddress());
    }

    public void back(View view) {
        Intent intent=new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
}
