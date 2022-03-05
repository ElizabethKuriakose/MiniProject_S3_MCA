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

public class ViewApplication extends AppCompatActivity {

    DBhelp db;
    ViewGroup ul;
    TextView[] rowTextView;
    Button[] rowButton;
    String[] aid;
    String[] users;

    String status="Pending";
    int i, ids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_application);

        db=new DBhelp(this);

        ul = findViewById(R.id.appview);
        ViewAppAdmin();
    }

    private void ViewAppAdmin() {
        Cursor res = db.getAppData(status);
        if (res.getCount() == 0) {
            showMessage("Error", "Nothing Found!");
            return;
        }
        rowTextView = new TextView[3];
        rowButton = new Button[res.getCount()];

        aid = new String[res.getCount() + 1];
        users = new String[res.getCount() + 1];
        i = 0;
        while (res.moveToNext()) {
            rowTextView[0] = new TextView(this);
            aid[i+1] = res.getString(0);
            rowTextView[0].setText("Application ID : " + res.getString(0) + "\n" );

            users[i+1] = res.getString(1);

            rowTextView[1] = new TextView(this);
            rowTextView[1].setText("SCHEME ID : " + res.getString(2 ) + "\n" );


            for (int j = 0; j < 2; j++) {
                ul.addView(rowTextView[j]);
            }
            rowButton[i] = new Button(this);
            rowButton[i].setId(i + 1);
            rowButton[i].setText("VARIFY");

            ul.addView(rowButton[i]);
            rowButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(ids=1;ids<=i;ids++) {

                        if (view.getId() == ids) {
                            //Toast.makeText(getApplicationContext(),aid[ids], Toast.LENGTH_LONG).show();
                            Intent in = new Intent(getApplicationContext(), VarifyApplication.class);
                            in.putExtra("aid",aid[ids]);
                            in.putExtra("us",users[ids]);
                            startActivity(in);

                        }
                    }
                }
            });
            i++;
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