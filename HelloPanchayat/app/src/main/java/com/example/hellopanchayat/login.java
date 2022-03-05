package com.example.hellopanchayat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    EditText en,ep;
    Button b1,b2;
    DBhelp db;
    String st = "Approved";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        en = findViewById(R.id.uname);
        ep = findViewById(R.id.pwd);

        b1=(Button) findViewById(R.id.log_b1);

        db=new DBhelp(login.this);
        Login();

        b2=(Button) findViewById(R.id.log_b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(login.this,registration.class);
                startActivity(i3);
            }
        });
        }

    private void Login() {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean log=db.loginCheck(en.getText().toString(),ep.getText().toString(),st);
                if((en.getText().toString().equals("Admin")) && (ep.getText().toString().equals("Admin@12")))
                {
                    Intent i=new Intent(login.this, com.example.hellopanchayat.AdminHome.class);
                    startActivity(i);
                }
                else if(log == true)
                {
                    Intent i2=new Intent(login.this, com.example.hellopanchayat.UserHome.class);
                    i2.putExtra("uid",en.getText().toString());
                    startActivity(i2);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Invalid User!!", Toast.LENGTH_SHORT).show();
                }
                en.setText(null);
                ep.setText(null);

            }
        });
    }

}