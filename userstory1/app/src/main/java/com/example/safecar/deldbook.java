package com.example.safecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class deldbook extends AppCompatActivity {

    TextView dstatus,did,dname;
    Button delete;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deldbook);


        db=new DatabaseHelper(this);

        delete = findViewById(R.id.ddelbook);


        did = findViewById(R.id.did);
        dname = findViewById(R.id.dname);

        Intent intent = getIntent();
        final String drivid = intent.getStringExtra("did");
        final String drivname = intent.getStringExtra("dname");


        did.setText(drivid);
        dname.setText(drivname);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(getApplicationContext(), Home.class);
        startActivity(in);
        finish();
    }
}