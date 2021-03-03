package com.example.safecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class delcbook extends AppCompatActivity {
    TextView cstatus,cid1,carname1;
    Button delete;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delcbook);

        db=new DatabaseHelper(this);

        delete = findViewById(R.id.cdelbook);
        cid1 = findViewById(R.id.cid);
        carname1 = findViewById(R.id.cname);

        Intent intent = getIntent();
        final String carid = intent.getStringExtra("cid");
        final String cname = intent.getStringExtra("cname");

        cid1.setText(carid);
        carname1.setText(cname);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(getApplicationContext(), Home.class);
        startActivity(in);
        finish();
    }
}