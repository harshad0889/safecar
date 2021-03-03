package com.example.safecar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class confirmdriver extends AppCompatActivity {

    EditText dpickloc,ddroplock,ddatepick,ddatedrop,drivid,dname,userid;
    Button dsubmit,dcancel;
    private int tdate,tmonth,tyear,fdate,fmonth,fyear;
    ListView lv,lv2;
    String  username,spickloc,sdroploc,spdate,sddate,usid,scid,drivername,uid;
    DatabaseHelper db;
    SharedPreferences sp;
    Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmdriver);

        db = new DatabaseHelper(this);


        sp = getSharedPreferences("user_details",MODE_PRIVATE);
        uid = sp.getString("uid",null);
        username  = sp.getString("username",null);

        tb = findViewById(R.id.appbar);
        setSupportActionBar(tb);
        ActionBar actionBar = getSupportActionBar();
        // actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("RENT DRIVER");

        ddatepick = findViewById(R.id.ddatepick);
        dpickloc = findViewById(R.id.dpickloc);
        ddroplock = findViewById(R.id.ddroploc);
        ddatedrop = findViewById(R.id.ddatedrop);
        lv2 = findViewById(R.id.listcity2);
        dsubmit = findViewById(R.id.dsubmit);
        drivid = findViewById(R.id.drivid);
        dname = findViewById(R.id.drivname);
        userid = findViewById(R.id.userid);
        //duser = findViewById(R.id.dusername);


        Intent intent = getIntent();

        String did = intent.getStringExtra("drivid");
        final String drivname = intent.getStringExtra("drivname");
        drivid.setText(did);
        dname.setText(drivname);
        userid.setText(uid);
        //duser.setText(dusername);



        lv2 = new ListView(this);
        List<String> data = new ArrayList<>();
        data.add("calicut");
        data.add("malapuram");
        data.add("wayanad");
        data.add("kochi");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
        lv2.setAdapter(adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(confirmdriver.this);
        builder.setCancelable(true);
        builder.setView(lv2);
        final  AlertDialog dialog = builder.create();


        //pickdate listener #1
        dpickloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();


                lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        dpickloc.setText(adapter.getItem(position));
                        dialog.dismiss();

                    }
                });


            }
        });

        //dropdate

        ddroplock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();


                lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                       ddroplock.setText(adapter.getItem(position));
                        dialog.dismiss();

                    }
                });


            }
        });

        //pickdatelistener #3
        ddatepick.setClickable(true);
        ddatepick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cal = Calendar.getInstance();
                fdate = cal.get(Calendar.DATE);
                fmonth = cal.get(Calendar.MONTH);
                fyear = cal.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(confirmdriver.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        String fdate = date+"-"+month+"-"+year;
                        ddatepick.setText(fdate);


                    }
                },fyear,fmonth,fdate);
                datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());
                datePickerDialog.show();
            }
        });

        //datedrop #4

        ddatedrop.setClickable(true);
        ddatedrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cal = Calendar.getInstance();
                tdate = cal.get(Calendar.DATE);
                tmonth = cal.get(Calendar.MONTH);
                tyear = cal.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(confirmdriver.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        String tdate = date+"-"+month+"-"+year;

                        ddatedrop.setText(tdate);


                    }
                },tyear,tmonth,tdate);
                datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 1);
                datePickerDialog.show();
            }
        });


        dsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dpickloc.length() == 0) {
                    dpickloc.requestFocus();
                   dpickloc.setError("please select pickup city");
                } else if (ddroplock.length() == 0) {
                    ddroplock.requestFocus();
                    ddroplock.setError("please select drop city");
                } else if (ddatepick.length() == 0) {
                    ddatepick.requestFocus();
                    ddatepick.setError(" select pick date");
                }else if (ddatedrop.length() == 0) {
                    ddatedrop.requestFocus();
                    ddatedrop.setError(" select drop date");
                }

                else

                {

                    usid=userid.getText().toString();
                    spickloc=dpickloc.getText().toString();
                    sdroploc=ddroplock.getText().toString();
                    spdate=ddatepick.getText().toString();
                    sddate=ddatedrop.getText().toString();
                    scid=drivid.getText().toString();
                    drivername=dname.getText().toString();
                    String dstatus = "booked";


                    db.insertdnotif(username, spickloc, sdroploc, spdate, sddate,usid, scid,drivername,dstatus);
                   // Adddnotif(username,spickloc, sdroploc, spdate, sddate, uid,scid);
                    dalert(view);

               /* Intent r = new Intent(confirmcar.this, Home.class);
                startActivity(r);
                finish();*/
                }

            }

            private void Adddnotif (String username,String spickloc, String sdroploc, String spdate, String
                    sddate,String usid, String scid,String dname,String dstatus) {

                boolean insertnotif = db.insertdnotif(username, spickloc, sdroploc, spdate, sddate,usid, scid,dname,dstatus);
            }

            });


    }
    public void dalert(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Happy renting!..");
        builder.setMessage("driver rented succesfully..!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent r = new Intent(confirmdriver.this, Home.class);
                startActivity(r);
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();




    }
}