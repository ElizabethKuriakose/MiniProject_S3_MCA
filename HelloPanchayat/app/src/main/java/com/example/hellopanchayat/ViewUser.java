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

public class ViewUser extends AppCompatActivity {

    com.example.hellopanchayat.DBhelp db;
    ViewGroup ul;
    TextView[] rowTextView;
    Button[] rowButton;
    String[] e;
    int i, ids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);
        db=new com.example.hellopanchayat.DBhelp(this);

        ul = findViewById(R.id.userlayout);
        ViewAllData();

    }
    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void ViewAllData() {
        Cursor res = db.getAllData();
        if (res.getCount() == 0) {
            showMessage("Error", "Nothing Found!");
            return;
        }
        rowTextView = new TextView[11];
        rowButton = new Button[res.getCount()];

        e = new String[res.getCount() + 1];
        i = 0;
        while (res.moveToNext()) {
            rowTextView[0] = new TextView(this);
            rowTextView[0].setText("Name                 : " + res.getString(0) + "\n" );

            rowTextView[1] = new TextView(this);
            e[i+1] = res.getString(1);
            rowTextView[1].setText("Email                  : " + res.getString(1) + "\n" );


            //rowTextView[2] = new TextView(this);
            //rowTextView[2].setText("Password          : " + res.getString(2));

            rowTextView[2] = new TextView(this);
            rowTextView[2].setText("DOB                    : " + res.getString(3) + "\n" );

            rowTextView[3] = new TextView(this);
            rowTextView[3].setText("Gender               : " + res.getString(4) + "\n" );

            rowTextView[4] = new TextView(this);
            rowTextView[4].setText("Poverty Line      : " + res.getString(5) + "\n" );

            rowTextView[5] = new TextView(this);
            rowTextView[5].setText("Category            : " + res.getString(6) + "\n" );

            rowTextView[6] = new TextView(this);
            rowTextView[6].setText("Address             : " + res.getString(7) + "\n" );

            rowTextView[7] = new TextView(this);
            rowTextView[7].setText("Religion              : " + res.getString(8) + "\n" );

            rowTextView[8] = new TextView(this);
            rowTextView[8].setText("Phone                 : " + res.getString(9) + "\n" );

            rowTextView[9] = new TextView(this);
            rowTextView[9].setText("Annual Income  : " + res.getString(10) + "\n" );

            rowTextView[10] = new TextView(this);
            rowTextView[10].setText("Status                 : " + res.getString(11) + "\n" );

            for (int j = 0; j < 11; j++) {
                ul.addView(rowTextView[j]);
            }
            rowButton[i] = new Button(this);
            rowButton[i].setId(i + 1);
            rowButton[i].setText("VERIFY ");

            ul.addView(rowButton[i]);
            rowButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(ids=1;ids<=i;ids++) {

                        if (view.getId() == ids) {
                            //Toast.makeText(getApplicationContext(), e[ids], Toast.LENGTH_LONG).show();
                            Intent in = new Intent(getApplicationContext(), UpdateUStatus.class);
                            in.putExtra("email",e[ids]);
                            startActivity(in);
                        }
                    }
                }
            });
            i++;
        }
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