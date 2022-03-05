package com.example.hellopanchayat;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class VarifyApplication extends AppCompatActivity {

    com.example.hellopanchayat.DBhelp db;
    String aid,usr;
    Button ap, re,vu;
    String status = "Approved";
    TextView t1,t2,t3,t4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varify_application);

        Bundle b = getIntent().getExtras();
        aid = (String) b.get("aid");
        usr = (String) b.get("us");
        db = new com.example.hellopanchayat.DBhelp(this);

       //t1.findViewById(R.id.aid);
        //t2.findViewById(R.id.sid);
       // t3.findViewById(R.id.applicant);
        //t4.findViewById(R.id.aid);

        ap = findViewById(R.id.var);
        re = findViewById(R.id.reject);
        vu = findViewById(R.id.vw);
        //ViewApp();
        ViewAllData();
        UpdateApplication(aid);
    }

    private void UpdateApplication(String aid) {
        ap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status = "Approved";
                boolean isUpdate = db.UpdateAPP(aid, status);
                if (isUpdate == true) {
                    Toast.makeText(VarifyApplication.this, "Varified", Toast.LENGTH_SHORT).show();
                    ap.setText("Varified");
                    ap.setBackgroundColor(Color.parseColor("#FFBB86FC"));
                }
                else
                    Toast.makeText(VarifyApplication.this, "Data Not Updated", Toast.LENGTH_SHORT).show();

            }
        });
        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status="Rejected";
                boolean isUpdate = db.ApproveUser(aid,status);
                if(isUpdate == true) {
                    Toast.makeText(VarifyApplication.this, "Rejected", Toast.LENGTH_SHORT).show();
                    re.setText("Rejected");
                    re.setBackgroundColor(Color.parseColor("#FFBB86FC"));
                }
                else
                    Toast.makeText(VarifyApplication.this,"Data Not Updated",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void ViewAllData(){

        vu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res =db.getAllData1(usr);
                if(res.getCount() == 0){
                    showMessage("Error","Nothing Found!");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("Name                 : " + res.getString(0) + "\n" );
                    buffer.append("Email                  : " + res.getString(1) + "\n" );
                    buffer.append("DOB                    : " + res.getString(3) + "\n" );
                    buffer.append("Gender               : " + res.getString(4) + "\n" );
                    buffer.append("Poverty Line      : " + res.getString(5) + "\n" );
                    buffer.append("Category            : " + res.getString(6) + "\n" );
                    buffer.append("Address             : " + res.getString(7) + "\n" );
                    buffer.append("Religion              : " + res.getString(8) + "\n" );
                    buffer.append("Phone                 : " + res.getString(9) + "\n" );
                    buffer.append("Annual Income  : " + res.getString(10) + "\n" );
                    buffer.append("Status                 : " + res.getString(11) + "\n" );

                }
                showMessage("Applicant",buffer.toString());
            }
        });

    }
    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
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