package com.example.sushma.vegetablemart;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sushma.vegetablemart.Databases.SignUpDatabase;

public class LoginActivity extends AppCompatActivity {
    TextView tv1,tv2,tv3;
    EditText ed1,ed2;
    SignUpDatabase db;
    String mobile,password;
    public static SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tv1=findViewById(R.id.textView);//forgot password
        tv2=findViewById(R.id.textView2);//signUp
        tv3=findViewById(R.id.textView47);//vendor Login
        ed1=findViewById(R.id.editText);
        ed2=findViewById(R.id.editText2);
        db=new SignUpDatabase(this);
        sp=getSharedPreferences("loginCheck",0);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        boolean b=sp.getBoolean("key",false);
        if(b==true){
            Intent intent=new Intent(this,HomeActivity.class);
            startActivity(intent);
        }
    }

    public void login(View view) {
        mobile=ed1.getText().toString();
        password=ed2.getText().toString();
        if(db.loginCheck(mobile,password)){
            SharedPreferences.Editor editor=sp.edit();
            editor.putString("mobile",mobile);
            editor.putString("password",password);
            editor.putBoolean("key",true);
            editor.commit();
            Intent intent=new Intent(this,HomeActivity.class);
            startActivity(intent);
        }
        else {
            final AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("TRY AGAIN").setMessage("Mobile number or Password is invalid").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    builder.setCancelable(true);
                }
            }).setCancelable(false).show();
        }
    }
}
