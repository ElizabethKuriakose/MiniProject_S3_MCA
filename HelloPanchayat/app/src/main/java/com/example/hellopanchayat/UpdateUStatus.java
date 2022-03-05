package com.example.hellopanchayat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateUStatus extends AppCompatActivity {

    com.example.hellopanchayat.DBhelp db;
    String user;
    Button ap,re;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ustatus);
        Bundle b=getIntent().getExtras();
        user = (String) b.get("email");
        db=new com.example.hellopanchayat.DBhelp(this);
        ap=findViewById(R.id.approve);
        re=findViewById(R.id.reject);
        UpdateUserstatus(user);
    }

    private void UpdateUserstatus(String user) {
        ap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status="Approved";
                boolean isUpdate = db.ApproveUser(user,status);
                if(isUpdate == true)
                    Toast.makeText(UpdateUStatus.this,"Updated",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(UpdateUStatus.this,"Data Not Updated",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), com.example.hellopanchayat.ViewUser.class);
                startActivity(i);

            }
        });
        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status="Rejected";
                boolean isUpdate = db.ApproveUser(user,status);
                if(isUpdate == true)
                    Toast.makeText(UpdateUStatus.this,"Updated",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(UpdateUStatus.this,"Data Not Updated",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), com.example.hellopanchayat.ViewUser.class);
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

}