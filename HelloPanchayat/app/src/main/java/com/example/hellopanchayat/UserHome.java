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

public class UserHome extends AppCompatActivity {

    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);


        //Buttons
        Button b1=(Button) findViewById(R.id.us_b1);
        Button b2=(Button) findViewById(R.id.us_b2);
        Button b3=(Button) findViewById(R.id.us_b3);

        Bundle bundle=getIntent().getExtras();
        uid = (String) bundle.get("uid");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHome.this,ViewServices.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserHome.this,uid,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UserHome.this,ViewSchemeUser.class);
                intent.putExtra("uid",uid);
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserHome.this,uid,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UserHome.this,Application_User_View.class);
                intent.putExtra("uid",uid);
                startActivity(intent);
            }
        });
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // Toast.makeText(this,"Selected: "+item.getTitle(),Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.nav_logout:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}