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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Carview2 extends AppCompatActivity {

    DatabaseHelper db;
    SharedPreferences cu;
    String cuid;
    Toolbar tb;

    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[]{db.COL_cid,
            db.COL_cbrand, db.COL_cmodel,db.COL_camount,db.COL_cagency,db.COL_ckms,db.COL_cphone,db.COL_cloc,db.COL_cemail};

    final int[] to = new int[]{R.id.cuid, R.id.cubrand, R.id.cumodel,R.id.cuamount,R.id.cuagency,R.id.cukms,R.id.cuphno, R.id.culocation, R.id.cuemail};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carview2);

        cu = getSharedPreferences("user_details",MODE_PRIVATE);
        cuid = cu.getString("uid",null);

        tb = findViewById(R.id.appbar);
        setSupportActionBar(tb);
        ActionBar actionBar = getSupportActionBar();
        // actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("UPDATE CAR");

        db = new DatabaseHelper(this);
        Cursor cursor = db.carlist(cuid);

        listView = findViewById(R.id.carulist);

        adapter = new SimpleCursorAdapter(this,R.layout.carulist, cursor, from, to,0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView cuid = view.findViewById(R.id.cuid);
                TextView cubrand = (TextView) view.findViewById(R.id.cubrand);
                TextView cumodel = (TextView) view.findViewById(R.id.cumodel);
                TextView cuamount = (TextView) view.findViewById(R.id.cuamount);
                TextView cuagency = (TextView) view.findViewById(R.id.cuagency);
                TextView cukms = (TextView) view.findViewById(R.id.cukms);
                TextView cuphno = (TextView) view.findViewById(R.id.cuphno);
                TextView culocation = (TextView) view.findViewById(R.id.culocation);
                TextView cuemail = (TextView) view.findViewById(R.id.cuemail);

               // ImageView uvimage = view.findViewById(R.id.uimgdriver);



                String sid = cuid.getText().toString();
                String sbrand = cubrand.getText().toString();
                String smodel = cumodel.getText().toString();
                String samount = cuamount.getText().toString();
                String sagency = cuagency.getText().toString();
                String skms = cukms.getText().toString();
                String sphno = cuphno.getText().toString();
                String slocation = culocation.getText().toString();
                String semail = cuemail.getText().toString();



                Intent s = new Intent(Carview2.this,Updatecar.class);
                s.putExtra("sid",sid);
                s.putExtra("sbrand",sbrand);
                s.putExtra("smodel",smodel);
                s.putExtra("samount",samount);
                s.putExtra("sagency",sagency);
                s.putExtra("skms",skms);
                s.putExtra("sphno",sphno);
                s.putExtra("slocation",slocation);
                s.putExtra("semail",semail);



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