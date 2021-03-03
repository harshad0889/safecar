package com.example.safecar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Home extends AppCompatActivity  {

    DatabaseHelper db2;
   private DrawerLayout dl;
   private NavigationView nv;
   private Toolbar tb;
    EditText loc,pick,drop;
    Button car,driver;
    ImageView dateic1,dateic2,locic;
    private int mdate,mmonth,myear;
    String datepick,datedrop;
    ListView lvcity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db2 = new DatabaseHelper(this);
        dl = findViewById(R.id.drawer);
        nv = findViewById(R.id.nav);
        tb=findViewById(R.id.appbar);
        loc=findViewById(R.id.location);
        pick=findViewById(R.id.dates);
        drop=findViewById(R.id.datedrop);
        car=findViewById(R.id.findcar);
        driver=findViewById(R.id.finddriver);
        dateic1=findViewById(R.id.datepickic);
        dateic2=findViewById(R.id.datedropic);
        locic=findViewById(R.id.locic);

        lvcity = findViewById(R.id.lvcity);
        lvcity = new ListView(this);
        List<String> data = new ArrayList<>();
        data.add("calicut");
        data.add("malapuram");
        data.add("wayanad");
        data.add("kochi");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
        lvcity.setAdapter(adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
        builder.setCancelable(true);
        builder.setView(lvcity);
        final  AlertDialog dialog = builder.create();


        setSupportActionBar(tb);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menuicon);



        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();


                lvcity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        loc.setText(adapter.getItem(position));
                        dialog.dismiss();
                       

                    }
                });
            }
        });

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id){

                    case R.id.carbooking:
                        Intent cbook = new Intent(Home.this, bookedcars.class);
                        startActivity(cbook);
                        finish();
                        return true;
                    case R.id.addcar:
                        Intent main = new Intent(Home.this, Addcar.class);
                        startActivity(main);
                        finish();

                        return true;
                    case R.id.driverbooking:
                        Intent dbook = new Intent(Home.this, bookeddrivers.class);
                        startActivity(dbook);
                        finish();
                        return true;
                    case R.id.adddriver:
                        Intent intent= new Intent(Home.this,Adddriver.class);
                        startActivity(intent);
                        finish();

                        return true;
                    case R.id.notification:
                        Intent s = new Intent(Home.this, notifview.class);
                        startActivity(s);
                        finish();

                        return true;
                    case R.id.dnotification:
                        Intent d = new Intent(Home.this, notifdriview.class);
                        startActivity(d);
                        finish();

                        return true;
                    case R.id.updatecar:
                        Intent m = new Intent(Home.this, Carview2.class);
                        startActivity(m);
                        finish();

                        return true;
                    case R.id.updatedriver:
                        Intent r = new Intent(Home.this, Driverview2.class);
                        startActivity(r);
                        finish();

                        return true;
                    case R.id.about:
                        Intent a = new Intent(Home.this, About.class);
                        startActivity(a);

                        return true;
                    case R.id.logout:
                        Intent z = new Intent(Home.this, Login.class);
                        startActivity(z);
                        finish();

                }

                return true;
            }
        });


        dateic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cal = Calendar.getInstance();
                mdate = cal.get(Calendar.DATE);
                mmonth = cal.get(Calendar.MONTH);
                myear = cal.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Home.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        pick.setText(date+"-"+month+"-"+year);


                    }
                },myear,mmonth,mdate);
                datePickerDialog.show();


            }
        });
        dateic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cal = Calendar.getInstance();
                mdate = cal.get(Calendar.DATE);
                mmonth = cal.get(Calendar.MONTH);
                myear = cal.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Home.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month,int date) {
                        drop.setText(date+"-"+month+"-"+year);

                    }
                },myear,mmonth,mdate);
                datePickerDialog.show();

            }
        });


        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*   datepick = pick.getText().toString();
                datedrop = drop.getText().toString();
                db2.insertdatedata(datepick, datedrop);
                Toast.makeText(Home.this, "Date added ", Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, Toast.LENGTH_LONG);

*/
                if (loc.length() == 0) {
                    loc.requestFocus();
                    loc.setError("please select your city");
                }
                else {
                    Intent r = new Intent(Home.this, CarView.class);
                    String location = loc.getText().toString();
                    r.putExtra("location", location);
                    startActivity(r);
                }
            }
        });

driver.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (loc.length() == 0) {
            loc.requestFocus();
            loc.setError("please select your city");
        }
        else {

            Intent a = new Intent(Home.this, DriverView.class);
            String location = loc.getText().toString();
            a.putExtra("location", location);
            startActivity(a);
        }
        //finish();
    }
});


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {

            case android.R.id.home:
                dl.openDrawer(GravityCompat.START);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(getApplicationContext(), Login.class);
        startActivity(in);
        finish();
    }
}