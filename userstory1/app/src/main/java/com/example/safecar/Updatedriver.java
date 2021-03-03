package com.example.safecar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Updatedriver extends AppCompatActivity {

    EditText dtname,dtaddress,dtage,dtprice,dtbadge,dtlocation,dtyoe,dtphno,dtemail,dtid;
    ImageView dtiv;
    RadioButton dtgender1,dtgender2,selectmode, selectedGender;;
    RadioGroup dtgender;
    Button addimage,dupdate,ddelete;
    String  did,dname,daddress,dage,dgender,dcharge,dbadge,dlocation,dyoe,dphno,demail, dadddriver,ddriverreset,daddimage,uid;
    SharedPreferences sp;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedriver);


        sp = getSharedPreferences("user_details",MODE_PRIVATE);
        uid = sp.getString("uid",null);

       // edittexts
        db = new DatabaseHelper(this);
        dtid = (EditText) findViewById(R.id.dtid);
        dtname = (EditText) findViewById(R.id.dtname);
        dtaddress = findViewById(R.id.dtaddress);
        dtage = findViewById(R.id.dtage);
        dtgender1 = findViewById(R.id.dtgender1);
        dtgender2=findViewById(R.id.dtgender2);
        dtgender=(RadioGroup) findViewById(R.id.dtgender);
        dtprice = findViewById(R.id.dtcharge);
        dtbadge = findViewById(R.id.dtbadge);
        dtlocation = findViewById(R.id.dtlocation);
        dtyoe = findViewById(R.id.dtyoe);
        dtphno = findViewById(R.id.dtphno);
        dtemail = findViewById(R.id.dtemail);
        dtiv = findViewById(R.id.dtiv);
        addimage = findViewById(R.id.addimage);
        dupdate = findViewById(R.id.dupdate);
        ddelete = findViewById(R.id.ddelete);




        ddelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                did = dtid.getText().toString();
                deletedriver2(did);
                Toast.makeText(Updatedriver.this, "Driver deleted succesfully ", Toast.LENGTH_LONG).show();

                Intent r = new Intent(Updatedriver.this, Home.class);
                startActivity(r);
                finish();


            }
            private void deletedriver2(String cid){
                int deletedriver = db.deletedriverdata(cid);
            }
        });



        //imgae chhose from gallery
        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ActivityCompat.requestPermissions
                        (Updatedriver.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                intent.setType("image/*");
                startActivityForResult(intent,1);

            }
        });



        Intent intent = getIntent();
        String sid = intent.getStringExtra("duid");
        String sname= intent.getStringExtra("duname");
        String saddress= intent.getStringExtra("duaddress");
        String sage= intent.getStringExtra("duage");
        String sgender= intent.getStringExtra("dugender");
        String sdprice= intent.getStringExtra("duprice");
        String sbadge= intent.getStringExtra("dubadge");
        String slocation= intent.getStringExtra("dulocation");
        String syoe= intent.getStringExtra("duyoe");
        String sphno= intent.getStringExtra("duphno");
        String semail= intent.getStringExtra("duemail");
       // Bitmap bitmap = intent.getParcelableExtra("dubitmap");
        String female = "Female";



        dtid.setText(sid);
        dtname.setText(sname);
        dtaddress.setText(saddress);
        dtage.setText(sage);
        if(female.equals(intent.getStringExtra("dugender"))){
            dtgender2.setChecked(true);
            dtgender1.setChecked(false);
        }
        else {
            dtgender1.setChecked(true);
            dtgender2.setChecked(false);
        }
        dtprice.setText(sdprice);
        dtbadge.setText(sbadge);
        dtlocation.setText(slocation);
        dtyoe.setText(syoe);
        dtphno.setText(sphno);
        dtemail.setText(semail);
        byte[] bytes = db.driverImage(sid);
        dtiv.setImageBitmap(getImage(bytes));



        dupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedGender = (RadioButton) findViewById(dtgender.getCheckedRadioButtonId());
                did = dtid.getText().toString();
                dname = dtname.getText().toString();
                daddress = dtaddress.getText().toString();
                dage = dtage.getText().toString();
                dgender = selectedGender.getText().toString();
                dcharge = dtprice.getText().toString();
                dbadge = dtbadge.getText().toString();
                dyoe = dtyoe.getText().toString();
                dlocation = dtlocation.getText().toString();
                dphno=dtphno.getText().toString();
                demail = dtemail.getText().toString();

                byte[] newentryimg = imageViewToByte(dtiv);


                updatedriver2(did,dname, daddress, dage, dgender, dcharge, dbadge, dlocation, dyoe,dphno,demail,uid, newentryimg);

                Toast.makeText(Updatedriver.this, "Driver updated succesfully ", Toast.LENGTH_LONG).show();
                Intent r = new Intent(Updatedriver.this, Home.class);
                startActivity(r);
                finish();
            }

            private void updatedriver2(String did,String dname, String daddress, String dage, String
                    dgender, String dcharge, String dbadge, String dlocation, String dyoe,
                                    String dphno, String demail,String uid, byte[] newentryimg) {


                int insertdriverdata = db.updatedriverdata(did,dname, daddress, dage, dgender, dcharge, dbadge, dlocation, dyoe, dphno, demail,uid, newentryimg);
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
        Intent in = new Intent(getApplicationContext(), Driverview2.class);
        startActivity(in);
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        Uri imageuri = data.getData();
        try {
            InputStream is = getContentResolver().openInputStream(imageuri);

            Bitmap bitmap = BitmapFactory.decodeStream(is);
            dtiv.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        super.onActivityResult(requestCode, resultCode, data);

    }



}
