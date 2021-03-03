package com.example.safecar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Driverview2 extends AppCompatActivity {


    DatabaseHelper db;
    SharedPreferences su;
    String uid;
    Toolbar tb;


    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[]{db.COL_did,
            db.COL_dname, db.COL_daddress,db.COL_dage,db.COL_dgender,db.COL_dcharge,db.COL_dbadges,db.COL_dlocation,db.COL_dyoe,db.COL_dphno,db.COL_demail};

    final int[] to = new int[]{R.id.duid, R.id.duname, R.id.duaddress,R.id.duage,R.id.dugender,R.id.ducharge,R.id.dubadge, R.id.dulocation, R.id.duyoe,R.id.duphno,R.id.duemail};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driverview2);

        su = getSharedPreferences("user_details",MODE_PRIVATE);
        uid = su.getString("uid",null);

        tb = findViewById(R.id.appbar);
        setSupportActionBar(tb);
        ActionBar actionBar = getSupportActionBar();
        // actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("UPDATE DRIVER");

        db = new DatabaseHelper(this);
        Cursor cursor = db.driverlist(uid);

        listView = (ListView) findViewById(R.id.driverulist);
        // listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.driverulist, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView duid = view.findViewById(R.id.duid);
                TextView duname = (TextView) view.findViewById(R.id.duname);
                TextView duaddress = (TextView) view.findViewById(R.id.duaddress);
                TextView duage = (TextView) view.findViewById(R.id.duage);
                TextView dugender = (TextView) view.findViewById(R.id.dugender);
                TextView ducharge = (TextView) view.findViewById(R.id.ducharge);
                TextView dubadge = (TextView) view.findViewById(R.id.dubadge);
                TextView dulocation = (TextView) view.findViewById(R.id.dulocation);
                TextView duyoe = (TextView) view.findViewById(R.id.duyoe);
                TextView duphno = (TextView) view.findViewById(R.id.duphno);
                TextView duemail = (TextView) view.findViewById(R.id.duemail);
                ImageView uvimage = view.findViewById(R.id.uimgdriver);



                String sid = duid.getText().toString();
                String sname = duname.getText().toString();
                String saddress = duaddress.getText().toString();
                String sage = duage.getText().toString();
                String sgender = dugender.getText().toString();
                String sdprice = ducharge.getText().toString();
                String sbadge = dubadge.getText().toString();
                String slocation = dulocation.getText().toString();
                String syoe = duyoe.getText().toString();
                String sphno = duphno.getText().toString();
                String semail = duemail.getText().toString();


                Intent s = new Intent(Driverview2.this,Updatedriver.class);
                s.putExtra("duid",sid);
                s.putExtra("duname",sname);
                s.putExtra("duaddress",saddress);
                s.putExtra("duage",sage);
                s.putExtra("dugender",sgender);
                s.putExtra("duprice",sdprice);
                s.putExtra("dubadge",sbadge);
                s.putExtra("dulocation",slocation);
                s.putExtra("duyoe",syoe);
                s.putExtra("duphno",sphno);
                s.putExtra("duemail",semail);


                startActivity(s);
                finish();


            }
        });







    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(getApplicationContext(), Home.class);
        startActivity(in);
        finish();
    }
}
