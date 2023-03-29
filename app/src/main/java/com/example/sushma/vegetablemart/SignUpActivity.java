package com.example.sushma.vegetablemart;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sushma.vegetablemart.Databases.SignUpDatabase;
import com.example.sushma.vegetablemart.Pozos.SignUpPozo;

public class SignUpActivity extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9;
    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7;
    Spinner sp1,sp2;
    String fname,lname,email,mobile,pass,confirmpass,state,city,address;
    public SignUpPozo pozo;
    SignUpDatabase signUpdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        tv1=findViewById(R.id.textView3);
        tv2=findViewById(R.id.textView4);
        tv3=findViewById(R.id.textView5);
        tv4=findViewById(R.id.textView6);
        tv5=findViewById(R.id.textView7);
        tv6=findViewById(R.id.textView8);
        tv7=findViewById(R.id.textView9);
        tv8=findViewById(R.id.textView10);
        tv9=findViewById(R.id.textView11);
        ed1=findViewById(R.id.editText3);
        ed2=findViewById(R.id.editText4);
        ed3=findViewById(R.id.editText5);
        ed4=findViewById(R.id.editText6);
        ed5=findViewById(R.id.editText7);
        ed6=findViewById(R.id.editText8);
        ed7=findViewById(R.id.editText9);
        sp1=findViewById(R.id.spinner);
        sp2=findViewById(R.id.spinner2);
        signUpdb=new SignUpDatabase(this);
        String[] states={"AndhraPradesh","Telangana","Delhi","MadhyaPradesh","Karnataka","Maharashtra"};
        String[] cities={"Hyderabad","Mumbai","Delhi","Bangalore","Mysore"};
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,states);
        sp1.setAdapter(adapter);
        ArrayAdapter adapter1=new ArrayAdapter(this,android.R.layout.simple_spinner_item,cities);
        sp2.setAdapter(adapter1);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                state=parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city=parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void register(View view) {
        fname=ed1.getText().toString();
        lname=ed2.getText().toString();
        email=ed3.getText().toString();
        mobile=ed4.getText().toString();
        pass=ed5.getText().toString();
        confirmpass=ed6.getText().toString();
        address=ed7.getText().toString();
        if(pass.equals(confirmpass)) {
            pozo = new SignUpPozo(fname, lname, email, mobile, pass, confirmpass, state, city, address);
            signUpdb.insertData(pozo);
            Toast.makeText(this, "data saved successfully", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
        else{
            final AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("TRY AGAIN").setMessage("Kindly check your Password").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    builder.setCancelable(true);
                }
            }).setCancelable(false).show();
        }
    }
}
