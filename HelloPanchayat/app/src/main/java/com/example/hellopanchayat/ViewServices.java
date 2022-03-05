package com.example.hellopanchayat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ViewServices extends AppCompatActivity {

    Button b1,b2,b3,b4,b5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_services);

        b1=findViewById(R.id.idbd);
        b2=findViewById(R.id.idft);
        b3=findViewById(R.id.idepay);
        b4=findViewById(R.id.idmarriage);
        b5=findViewById(R.id.idbuilding);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),WebServices.class);
                i.putExtra("url","https://cr.lsgkerala.gov.in/");
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),WebServices.class);
                i.putExtra("url","http://filetracking.lsgkerala.gov.in");
                startActivity(i);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),WebServices.class);
                i.putExtra("url","https://tax.lsgkerala.gov.in/epayment/index.php");
                startActivity(i);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),WebServices.class);
                i.putExtra("url","https://cr.lsgkerala.gov.in/Cmn_Application.php");
                startActivity(i);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),WebServices.class);
                i.putExtra("url","https://buildingpermit.lsgkerala.gov.in/");
                startActivity(i);
            }
        });
    }
}