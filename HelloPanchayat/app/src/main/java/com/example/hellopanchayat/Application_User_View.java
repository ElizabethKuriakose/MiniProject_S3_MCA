package com.example.hellopanchayat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Application_User_View extends AppCompatActivity {

    DBhelp db;
    ViewGroup ul;
    TextView[] rowTextView;

    String[] users;

    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_application);

        db=new DBhelp(this);

        ul = findViewById(R.id.appview);
        Bundle bundle=getIntent().getExtras();
        uid = (String) bundle.get("uid");

        ViewAppAdmin();
    }

    private void ViewAppAdmin() {
        Cursor res = db.getAppData1(uid);
        if (res.getCount() == 0) {
            showMessage("Error", "Nothing Found!");
            return;
        }
        rowTextView = new TextView[3];

        while (res.moveToNext()) {
            rowTextView[0] = new TextView(this);
            rowTextView[0].setText("Application ID : " + res.getString(0) + "\n" );


            rowTextView[1] = new TextView(this);
            rowTextView[1].setText("SCHEME ID : " + res.getString(2 ) + "\n" );

            rowTextView[2] = new TextView(this);
            rowTextView[2].setText("Status : " + res.getString(3 ) + "\n\n\n" );

            for (int j = 0; j < 3; j++) {
                ul.addView(rowTextView[j]);
            }
        }
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