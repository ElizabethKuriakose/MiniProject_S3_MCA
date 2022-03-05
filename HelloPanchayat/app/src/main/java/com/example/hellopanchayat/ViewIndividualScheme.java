package com.example.hellopanchayat;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ViewIndividualScheme extends AppCompatActivity {

    com.example.hellopanchayat.DBhelp db;
    String id,uid;
    TextView ldate,sname,scat,seli,sdoc,sid;
    Spinner spinner;
    Button apply;
    String status="Pending";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_individual_scheme);

        Bundle b=getIntent().getExtras();
        id = (String) b.get("sid");
        uid = (String) b.get("uid");


        db=new com.example.hellopanchayat.DBhelp(this);
        apply=findViewById(R.id.scheme_btn);
        sid=findViewById(R.id.sid);
        sname=findViewById(R.id.sname);
        scat=findViewById(R.id.scat);
        seli=findViewById(R.id.elig);
        sdoc=findViewById(R.id.doc);
        ldate=findViewById(R.id.ldate);

        setData();
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              boolean isappAdd = db.AddApplication(uid,id,status);
                if(isappAdd == true) {
                    Toast.makeText(ViewIndividualScheme.this, "Applied", Toast.LENGTH_SHORT).show();
                    apply.setText("Applied");
                    apply.setBackgroundColor(Color.parseColor("#FFBB86FC"));
                }
                else
                    Toast.makeText(ViewIndividualScheme.this,"Failed!!!",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void setData(){
        //Toast.makeText(UpdateScheme.this,id,Toast.LENGTH_SHORT).show();

        Cursor res = db.getSchemeDataIndividual(id);
        while(res.moveToNext()) {
            sid.setText("Scheme ID : " + res.getString(0));
            sname.setText("Scheme Name : " + res.getString(1));
            scat.setText("Category : " + res.getString(2));
            seli.setText("Eligibility : " + res.getString(3));
            sdoc.setText("Required Documents : " + res.getString(4));
            ldate.setText("Last Date to Apply : " + res.getString(5));
        }
    }
}