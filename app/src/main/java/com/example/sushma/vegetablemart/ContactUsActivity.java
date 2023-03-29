package com.example.sushma.vegetablemart;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        iv=findViewById(R.id.imageView4);
        iv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel: 7702969580"));
        startActivity(intent);
    }
}
