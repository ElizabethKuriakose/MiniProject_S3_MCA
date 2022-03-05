package com.example.hellopanchayat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AdminHome extends AppCompatActivity {

    DBhelp db;
    TextView[] rowTextView;
    Button[] rowButton;
    ViewGroup ul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        db = new DBhelp(AdminHome.this);


        //Buttons
        Button b1 = (Button) findViewById(R.id.ad_b1);
        Button b2 = (Button) findViewById(R.id.ad_b2);
        Button b3 = (Button) findViewById(R.id.ad_b3);
        Button b4 = (Button) findViewById(R.id.ad_b4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHome.this, ViewApplication.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHome.this, schemes.class);
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), com.example.hellopanchayat.ViewScheme.class);
                startActivity(i);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),ViewUser.class);
                startActivity(i);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // Toast.makeText(this,"Selected: "+item.getTitle(),Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.nav_services:
                Intent i = new Intent(getApplicationContext(), com.example.hellopanchayat.ViewServices.class);
                startActivity(i);
                return true;
            case R.id.nav_logout:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    /*public void ViewAllData(){
                Cursor res =db.getAllData();
                if(res.getCount() == 0){
                    showMessage("Error","Nothing Found!");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("\tName\t\t\t\t:"+res.getString(0)+"\n");
                    buffer.append("\tEmail\t\t\t\t:"+res.getString(1)+"\n");
                    buffer.append("\tPassword\t\t\t:"+res.getString(2)+"\n");
                    buffer.append("\tDOB \t\t\t\t:"+res.getString(3)+"\n");
                    buffer.append("\tGender\t\t\t:"+res.getString(4)+"\n");
                    buffer.append("\tPoverty Line\t:"+res.getString(5)+"\n");
                    buffer.append("\tCategory\t\t\t:"+res.getString(6)+"\n");
                    buffer.append("\tAddress\t\t\t:"+res.getString(7)+"\n");
                    buffer.append("\tReligion\t\t\t:"+res.getString(8)+"\n");
                    buffer.append("\tPhone \t\t\t:"+res.getString(8)+"\n");
                    buffer.append("\tAnnual Income\t\t:"+res.getString(10)+"\n");
                    buffer.append("\n\n");
                }
                showMessage("Users",buffer.toString());
            }*/


}



