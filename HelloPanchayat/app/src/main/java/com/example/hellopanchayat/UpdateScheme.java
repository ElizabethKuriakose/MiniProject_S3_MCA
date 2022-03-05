package com.example.hellopanchayat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class UpdateScheme extends AppCompatActivity {

    DBhelp db;
    String item,id;
    EditText ldate,sname,seli,sdoc;
    Spinner spinner;
    Button create,dlt;
    final Calendar myCalendar= Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_scheme);

        Bundle b=getIntent().getExtras();
        id = (String) b.get("sid");

        db=new DBhelp(this);
        create=findViewById(R.id.scheme_btn);
        dlt=findViewById(R.id.scheme_dlt);
        sname=findViewById(R.id.sname);
        seli=findViewById(R.id.elig);
        sdoc=findViewById(R.id.doc);
        ldate=findViewById(R.id.ldate);

        spinner = findViewById(R.id.spinner);
        List<String> relgn = new ArrayList<>();
        relgn.add(0, "--Select--");
        relgn.add("Aavas Yojana");
        relgn.add("Financial Assistance");
        relgn.add("Basic Services");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,relgn);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals(" ")){
                }else {
                    item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(),"Selected: " +item, Toast.LENGTH_SHORT);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

        });
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        ldate=(EditText)findViewById(R.id.ldate);
        ldate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(UpdateScheme.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        setData();
        deleteData();
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdate = db.UpdateScheme(id,sname.getText().toString(), item, seli.getText().toString(), sdoc.getText().toString(), ldate.getText().toString());
                if(isUpdate == true)
                    Toast.makeText(UpdateScheme.this,"Updated",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(UpdateScheme.this,"Data Not Updated",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteData() {
        dlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRows=db.deleteData(id);
                if(deletedRows>0)
                    Toast.makeText(UpdateScheme.this,"Data Deleted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(UpdateScheme.this,"Data Not Deleted",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setData(){
        //Toast.makeText(UpdateScheme.this,id,Toast.LENGTH_SHORT).show();

        Cursor res = db.getSchemeDataIndividual(id);
        while(res.moveToNext()) {
            sname.setText(res.getString(1));
            seli.setText(res.getString(3));
            sdoc.setText(res.getString(4));
            ldate.setText(res.getString(5));
        }
    }
    private void updateLabel(){
        String myFormat="dd/MM/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        ldate.setText(dateFormat.format(myCalendar.getTime()));
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