package com.example.safecar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

public class Adddriver extends AppCompatActivity {

    DatabaseHelper db;
    EditText name,address,age,charge,badges,yoe,location,email,phno;
    RadioGroup gender;
    RadioButton selectedGender;
    Button adddriver,driverreset,addimage;
    final int REQUEST_CODE_GALLERY = 999;
    SharedPreferences sp;
    ListView lvcity;
    Toolbar tb;
    private int mdate,mmonth,myear;

    ImageView v;
    String  dname,daddress,dage,dgender,dcharge,dbadge,dlocation,dyoe,dphno,demail, dadddriver,ddriverreset,daddimage,uid;


    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern
            .compile("[a-zA-Z0-9+._%-+]{1,256}" + "@"
                    + "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" + "(" + "."
                    + "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" + ")+");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddriver);

        sp = getSharedPreferences("user_details",MODE_PRIVATE);
        uid = sp.getString("uid",null);


        db = new DatabaseHelper(this);
        name = (EditText) findViewById(R.id.Name);
        address = (EditText) findViewById(R.id.Address);
        age = (EditText) findViewById(R.id.age);
        gender=(RadioGroup) findViewById(R.id.radioGender);
        charge = (EditText) findViewById(R.id.charge);
        badges = (EditText) findViewById(R.id.Badge);
        yoe = (EditText) findViewById(R.id.Yearofexperience);
        location= findViewById(R.id.Location);
        email =  findViewById(R.id.emaildriver);
        phno=(EditText)findViewById(R.id.pnno);
        adddriver =  findViewById(R.id.adddriver);
        driverreset= findViewById(R.id.driverreset);
        addimage =  findViewById(R.id.addimage);
        v = findViewById(R.id.v);


        tb = findViewById(R.id.appbar);
        setSupportActionBar(tb);
        ActionBar actionBar = getSupportActionBar();
        // actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("ADD DRIVER");


        lvcity = findViewById(R.id.lvcity);
        lvcity = new ListView(this);
        List<String> data = new ArrayList<>();
        data.add("calicut");
        data.add("malapuram");
        data.add("wayanad");
        data.add("kochi");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
        lvcity.setAdapter(adapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(Adddriver.this);
        builder.setCancelable(true);
        builder.setView(lvcity);
        final  AlertDialog dialog = builder.create();

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();


                lvcity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        location.setText(adapter.getItem(position));
                        dialog.dismiss();


                    }
                });
            }
        });


        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ActivityCompat.requestPermissions
                        (Adddriver.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                intent.setType("image/*");
                startActivityForResult(intent,1);

            }
        });

        age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cal = Calendar.getInstance();
                mdate = cal.get(Calendar.DATE);
                mmonth = cal.get(Calendar.MONTH);
                myear = cal.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Adddriver.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        age.setText(date+"-"+month+"-"+year);


                    }
                },myear,mmonth,mdate);
                datePickerDialog.show();
            }
        });

        driverreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(getApplicationContext(), Adddriver.class);
                startActivity(in);

            }
        });



        adddriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedGender = (RadioButton) findViewById(gender.getCheckedRadioButtonId());
                dname = name.getText().toString();
                daddress = address.getText().toString();
                dage = age.getText().toString();
                dgender = selectedGender.getText().toString();
                dcharge = charge.getText().toString();
                dbadge = badges.getText().toString();
                dyoe = yoe.getText().toString();
                dlocation = location.getText().toString();
                dphno=phno.getText().toString();
                demail = email.getText().toString();
                dadddriver = adddriver.getText().toString();
                ddriverreset = driverreset.getText().toString();
                byte[] newentryimg = imageViewToByte(v);

                if (name.length() == 0) {
                    name.requestFocus();
                    name.setError("please enter your name");
                } else if (address.length() == 0) {
                    address.requestFocus();
                    address.setError("please enter your address");
                }    else if (address.length() == 0) {
                        address.requestFocus();
                        address.setError("please enter your Age");
                } else if (email.length() == 0) {
                    email.requestFocus();
                    email.setError("please enter your email");
                } else if (!checkEmail(email.getText().toString())) {
                    email.requestFocus();
                    email.setError("please enter valid email address");
                } else if (phno.length() == 0) {
                    phno.requestFocus();
                    phno.setError("please enter your phone number");
                } else if (phno.length() != 10) {
                    phno.requestFocus();
                    phno.setError("please enter valid mobile number");
                } else if (charge.length() == 0) {
                    charge.requestFocus();
                    charge.setError("please enter your charge");

                } else if (location.length() == 0) {
                    location.requestFocus();
                    location.setError("please enter your location");
                }
                else if (badges.length() == 0) {
                    badges.requestFocus();
                    badges.setError("please enter your badges");
                } else if (yoe.length() == 0) {
                    yoe.requestFocus();
                    yoe.setError("please enter your year of experience");
                } else if (age.length() == 0) {
                        age.requestFocus();
                        age.setError("please enter your name");
                }
                  else if (yoe.length() == 0) {
                    yoe.requestFocus();
                    yoe.setError("please enter your location");
                } else {

                    Toast.makeText(Adddriver.this, "Driver added succesfully ", Toast.LENGTH_LONG).show();
                    Adddriver2(dname, daddress, dage, dgender, dcharge, dbadge, dlocation, dyoe,dphno,demail,uid, newentryimg);
                    Intent r = new Intent(Adddriver.this, Home.class);
                    startActivity(r);
                    finish();

                }





            }

            private void Adddriver2(String dname, String daddress, String dage, String
                    dgender, String dcharge, String dbadge, String dlocation, String dyoe,
                                    String dphno, String demail,String uid, byte[] newentryimg) {


                boolean insertdriverdata = db.insertdriverdata(dname, daddress, dage, dgender, dcharge, dbadge, dlocation, dyoe, dphno, demail,uid, newentryimg);
            }

            private byte[] imageViewToByte (ImageView v){

                Bitmap bitmap = ((BitmapDrawable) v.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                return byteArray;
            }

        });
    }

    private boolean checkEmail(String demail) {
        return EMAIL_ADDRESS_PATTERN.matcher(demail).matches();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        Uri imageuri = data.getData();
        try {
            InputStream is = getContentResolver().openInputStream(imageuri);

            Bitmap bitmap = BitmapFactory.decodeStream(is);
            v.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent in = new Intent(getApplicationContext(), Home.class);
        startActivity(in);
        finish();
    }
}
