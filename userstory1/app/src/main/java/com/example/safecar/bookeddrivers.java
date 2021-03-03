package com.example.safecar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class bookeddrivers extends AppCompatActivity {

    DatabaseHelper db;
    SharedPreferences cu;
    String dnuid,username;
    TextView driverid1,drivername1;
    Toolbar tb;

    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[]{db.COL_dnuid,db.COL_dusername,
            db.COL_dploc, db.COL_ddloc,db.COL_dpdate,db.COL_dddate,db.COL_dtime,db.COL_drivername,db.COL_dscid};

    final int[] to = new int[]{R.id.nuid,R.id.username,  R.id.locpick,R.id.locdrop,R.id.datepic,R.id.datedrop, R.id.time,R.id.driver,R.id.drivid};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookeddrivers);

        cu = getSharedPreferences("user_details",MODE_PRIVATE);
        dnuid = cu.getString("uid",null);
        username = cu.getString("username", null);
        tb = findViewById(R.id.appbar);
        setSupportActionBar(tb);
        ActionBar actionBar = getSupportActionBar();
        // actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("MY DRIVER BOOKINGS");

        db = new DatabaseHelper(this);
        Cursor cursor = db.dellist2(dnuid);

        listView = findViewById(R.id.dbooklv);

        adapter = new SimpleCursorAdapter(this,R.layout.cdlist, cursor, from, to,0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                driverid1 = view.findViewById(R.id.drivid);
                drivername1 = view.findViewById(R.id.driver);

                String did = driverid1.getText().toString();
                String dname = drivername1.getText().toString();

                Intent s = new Intent(bookeddrivers.this, deldbook.class);
                s.putExtra("did",did);
                s.putExtra("dname",dname);
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