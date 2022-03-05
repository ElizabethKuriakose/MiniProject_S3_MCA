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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ViewSchemeUser extends AppCompatActivity {
    DBhelp db;
    ViewGroup ul;
    TextView[] rowTextView;
    Button[] rowButton;
    String[] id;
    String uid;
    int i, ids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scheme_user);
        db=new DBhelp(this);

        Bundle bundle=getIntent().getExtras();
        uid = (String) bundle.get("uid");

        ul = findViewById(R.id.schemelayout);
        ViewSchemeData();
    }

    private void ViewSchemeData() {
        Cursor res = db.getSchemeData();
        if (res.getCount() == 0) {
            showMessage("Error", "Nothing Found!");
            return;
        }
        rowTextView = new TextView[6];
        rowButton = new Button[res.getCount()];

        id = new String[res.getCount() + 1];
        i = 0;
        while (res.moveToNext()) {
            rowTextView[0] = new TextView(this);
            id[i+1] = res.getString(0);
            rowTextView[0].setText("Scheme ID : " + res.getString(0) + "\n" );

            rowTextView[1] = new TextView(this);
            rowTextView[1].setText("SCHEME NAME : " + res.getString(1) + "\n" );


            rowTextView[2] = new TextView(this);
            rowTextView[2].setText("CATEGORY : " + res.getString(2) + "\n" );

            rowTextView[3] = new TextView(this);
            rowTextView[3].setText("ELIGIBILITY : " + res.getString(3) + "\n" );

            rowTextView[4] = new TextView(this);
            rowTextView[4].setText("REQUIRED DOCUMENTS : " + res.getString(4) + "\n" );

            rowTextView[5] = new TextView(this);
            rowTextView[5].setText("LAST DATE : "+ res.getString(5) + "\n");

            for (int j = 0; j < 6; j++) {
                ul.addView(rowTextView[j]);
            }
            rowButton[i] = new Button(this);
            rowButton[i].setId(i + 1);
            rowButton[i].setText("APPLY");

            ul.addView(rowButton[i]);
            rowButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(ids=1;ids<=i;ids++) {

                        if (view.getId() == ids) {
                            Toast.makeText(getApplicationContext(), uid, Toast.LENGTH_LONG).show();
                            Intent in = new Intent(getApplicationContext(), ViewIndividualScheme.class);
                            in.putExtra("sid",id[ids]);
                            in.putExtra("uid",uid);
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

