package com.example.sushma.vegetablemart;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.sushma.vegetablemart.Databases.SignUpDatabase;

public class ForgotPasswordActivity extends AppCompatActivity {
    SignUpDatabase db;
    String umobile;
    EditText ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ed = findViewById(R.id.editText12);
        db = new SignUpDatabase(this);
    }

    public void submit(View view) {
            umobile=ed.getText().toString();
            if(db.getMobile(umobile)){
                final AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("Your Password is:").setMessage(db.getPassword(umobile)).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(intent);
                    }
                }).setCancelable(false).show();
                final Thread thread=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialog) {
                                    builder.setCancelable(true);
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
            else{
                final AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("TRY AGAIN").setMessage("Mobile number is invalid").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builder.setCancelable(true);
                    }
                }).setCancelable(false).show();
            }
    }
}

