package com.example.safecar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.SharedElementCallback;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Updatecar extends AppCompatActivity {


    EditText ctbrand,ctmodel,ctamount,ctagency,ctkms,ctphno,ctlocation,ctemail,ctid;
    ImageView civ;
    Button addcimage,cupdate,cdelete;
    SharedPreferences sp;
    String cid,cbrand,cmodel,camount,cagency,ckms,cphone,cloc,cemail, caddcar,ccarreset,caddimage,uid;


    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatecar);

        sp = getSharedPreferences("user_details",MODE_PRIVATE);
        uid = sp.getString("uid",null);

        db = new DatabaseHelper(this);
        ctid = findViewById(R.id.cid);
        ctbrand = findViewById(R.id.cbrand);
        ctmodel = findViewById(R.id.cmodel);
        ctamount = findViewById(R.id.camount);
        ctagency = findViewById(R.id.cagency);
        ctkms=findViewById(R.id.ckms);
        ctphno = findViewById(R.id.cphno);
        ctlocation = findViewById(R.id.cloc);
        ctemail = findViewById(R.id.cemail);
        civ = findViewById(R.id.civ);
         addcimage = findViewById(R.id.addcimage);
        cupdate = findViewById(R.id.cupdate);
        cdelete = findViewById(R.id.cdelete);


        cdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cid = ctid.getText().toString();
                deletecar2(cid);
                Toast.makeText(Updatecar.this, "Driver deleted succesfully ", Toast.LENGTH_LONG).show();

                Intent r = new Intent(Updatecar.this, Home.class);
                startActivity(r);
                finish();


            }
            private void deletecar2(String cid){
                int deletecar = db.deletecardata(cid);

            }
        });


         addcimage.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 ActivityCompat.requestPermissions
                         (Updatecar.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);

                 Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                 intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                 intent.setType("image/*");
                 startActivityForResult(intent,1);
             }
         });


        Intent intent = getIntent();
        String sid = intent.getStringExtra("sid");
        String sbrand= intent.getStringExtra("sbrand");
        String smodel= intent.getStringExtra("smodel");
        String samount= intent.getStringExtra("samount");
        String sagency= intent.getStringExtra("sagency");
        String skms= intent.getStringExtra("skms");
        String sphno= intent.getStringExtra("sphno");
        String slocation= intent.getStringExtra("slocation");
        String semail= intent.getStringExtra("semail");


        ctid.setText(sid);
        ctbrand.setText(sbrand);
        ctmodel.setText(smodel);
        ctamount.setText(samount);
        ctagency.setText(sagency);
        ctkms.setText(skms);
        ctphno.setText(sphno);
        ctlocation.setText(slocation);
        ctemail.setText(semail);
        byte[] bytes = db.carImage(sid);
        civ.setImageBitmap(getImage(bytes));




        cupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cid = ctid.getText().toString();
                cbrand = ctbrand.getText().toString();
                cmodel = ctmodel.getText().toString();
                camount = ctamount.getText().toString();
                cagency = ctagency.getText().toString();
                ckms = ctkms.getText().toString();
                cphone = ctphno.getText().toString();
                cloc = ctlocation.getText().toString();
                cemail = ctemail.getText().toString();

                byte[] newentryimg = imageViewToByte(civ);

                updatecar2(cid,cbrand, cmodel, camount, cagency, ckms, cphone, cloc, cemail,uid, newentryimg);
                Toast.makeText(Updatecar.this, "car updated succesfully ", Toast.LENGTH_LONG).show();
                Intent r = new Intent(Updatecar.this, Home.class);
                startActivity(r);
                finish();
            }

            private void updatecar2(String cid,String cbrand, String cmodel, String camount, String
                    cagency, String ckms, String cphone, String cloc, String cemail,String uid,
                                  byte[] newentryimg){

                int insertcardata = db.updatecardata(cid,cbrand, cmodel, camount, cagency, ckms, cphone, cloc, cemail, uid, newentryimg);
            }




            private byte[] imageViewToByte (ImageView dtiv){

                Bitmap bitmap = ((BitmapDrawable) dtiv.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                return byteArray;
            }
        });
    }

    public static Bitmap getImage(byte[] image) {

        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(getApplicationContext(), Carview2.class);
        startActivity(in);
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        Uri imageuri = data.getData();
        try {
            InputStream is = getContentResolver().openInputStream(imageuri);

            Bitmap bitmap = BitmapFactory.decodeStream(is);
            civ.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        super.onActivityResult(requestCode, resultCode, data);

    }


}