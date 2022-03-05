package com.example.hellopanchayat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class registration extends AppCompatActivity {
    EditText name,email,pwd,dob,ads,phn,inc;
    Button reg;
    RadioGroup rGen,rPL,rCat;
    String gen="";
    String PL="";
    String cat="";
    String item="";
    String status="Pending";
    boolean isAllFieldsChecked;
    DBhelp db;
    final Calendar myCalendar= Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        pwd=findViewById(R.id.pwd);
        dob=findViewById(R.id.dob);
        ads=findViewById(R.id.ads);
        phn=findViewById(R.id.phn);
        inc=findViewById(R.id.income);

        rGen=findViewById(R.id.radioG);
        rPL=findViewById(R.id.radioPL);
        rCat=findViewById(R.id.radioCat);
        reg=findViewById(R.id.reg);

        db =  new DBhelp(registration.this);
        //viewData();

        rGen.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioM:
                        gen="Male";
                        break;
                    case R.id.radioF:
                        gen="Female";
                        break;
                    case R.id.radioOt:
                        gen="Others";
                        break;
                }
                Toast.makeText(getBaseContext(), gen , Toast.LENGTH_SHORT).show();
            }
        });
        rPL.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioA:
                        PL="APL";
                        break;
                    case R.id.radioB:
                        PL="BPL";
                        break;
                }
                Toast.makeText(getBaseContext(), PL , Toast.LENGTH_SHORT).show();
            }
        });
        rCat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radiogen:
                        cat="General";
                        break;
                    case R.id.radioobc:
                        cat="OBC";
                        break;
                    case R.id.radiooec:
                        cat="OEC";
                        break;
                    case R.id.radioscst:
                        cat="SC/ST";
                        break;
                }
                Toast.makeText(getBaseContext(), cat , Toast.LENGTH_SHORT).show();
            }
        });
        Spinner spinner = findViewById(R.id.spinner);
        List<String> relgn = new ArrayList<>();
        relgn.add(0, "--Select--");
        relgn.add("Christian");
        relgn.add("Hindu");
        relgn.add("Muslim");
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
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(registration.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        AddData();
        //ViewAllData();
        //DeleteData();
    }

    private void updateLabel(){
        String myFormat="dd/MM/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        dob.setText(dateFormat.format(myCalendar.getTime()));
    }

    public void AddData() {
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {
                    boolean isInserted = db.insertUData(name.getText().toString(), email.getText().toString(), pwd.getText().toString(), dob.getText().toString(), gen, PL, cat, ads.getText().toString(), item, phn.getText().toString(), inc.getText().toString(), status);
                    if (isInserted) {
                        Toast.makeText(registration.this, "Data Inserted ", Toast.LENGTH_LONG).show();
                        Intent inlog = new Intent(registration.this, com.example.hellopanchayat.login.class);
                        startActivity(inlog);
                    } else {
                        Toast.makeText(registration.this, "Something Went Wrong. Try again!!", Toast.LENGTH_SHORT).show();
                        name.setText(null);email.setText(null);pwd.setText(null);dob.setText(null);ads.setText(null);phn.setText(null);inc.setText(null);
                    }
                }

            }
        });
    }
    public void ViewAllData(){

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res =db.getAllData();
                if(res.getCount() == 0){
                    showMessage("Error","Nothing Found!");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("ID :"+res.getString(0)+"\n");
                    buffer.append("Name :"+res.getString(1)+"\n");
                    buffer.append("Email :"+res.getString(2)+"\n");
                    buffer.append("Password :"+res.getString(3)+"\n");
                    buffer.append("Phone :"+res.getString(4)+"\n");
                    // buffer.append("ID :"+res.getString(5)+"\n");
                }
                showMessage("Data",buffer.toString());
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
    private void DeleteData() {
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRows=db.deleteData(name.getText().toString());
                if(deletedRows>0)
                    Toast.makeText(registration.this,"Data Deleted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(registration.this,"Data Not Deleted",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private boolean CheckAllFields() {
        if (name.length() == 0) {
            name.setError("This field is required");
            return false;
        }

        if (email.length() == 0) {
            email.setError("This field is required");
            return false;
        }
        if (pwd.length() == 0) {
            pwd.setError("This field is required");
            return false;
        } else if (pwd.length() < 6) {
            pwd.setError("Password must be minimum 6 characters");
            return false;
        }
        if (dob.length() == 0) {
            dob.setError("This field is required");
            return false;
        }
        if (gen.length() == 0){
            Toast.makeText(this, "Please Select Your Gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (PL.length() == 0){
            Toast.makeText(this, "Poverty Line is Empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cat.length() == 0){
            Toast.makeText(this, "Please Select Category", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (ads.length() == 0) {
            ads.setError("This field is required");
            return false;
        }
        if (item.equals("--Select--") ){
            Toast.makeText(this, "Please Select Religion", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (phn.length() == 0) {
            phn.setError("This field is required");
            return false;
        } else if (phn.length() < 10 || phn.length() > 10) {
            phn.setError("Phone number must be 10 numbers");
            return false;
        }
        if (inc.length() == 0) {
            inc.setError("This field is required");
            return false;
        }
        return true;
    }


}