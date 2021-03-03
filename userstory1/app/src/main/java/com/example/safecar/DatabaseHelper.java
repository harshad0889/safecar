package com.example.safecar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "safe.db";
    public static final String TABLE_NAME = "registrationtable";
    public static final String COL_1 = "_id";
    public static final String COL_2 = "username";
    public static final String COL_3 = "address";
    public static final String COL_4 = "gender";
    public static final String COL_5 = "email";
    public static final String COL_6 = "phnno";
    public static final String COL_7 = "password";
    public static final String COL_8 = "location";
    public static final String TABLE_DATE = "datetable";
    public static final String COL_id = "D_id";
    public static final String COL_pick = "datepick";
    public static final String COL_drop = "datedrop";
    //car details
    public static final String TABLE_CAR = "cartable";
    public static final String COL_cid = "cid";
    public static final String COL_cbrand = "brand";
    public static final String COL_cmodel = "model";
    public static final String COL_camount = "amount";
    public static final String COL_cagency = "agency";
    public static final String COL_ckms = "kms";
    public static final String COL_cphone = "phone";
    public static final String COL_cloc = "loation";
    public static final String COL_cemail = "email";
    public static final String COL_iv = "iv";
    public static final String COL_cuid = "_id";
    //Driver details
    public static final String TABLE_DRIVER = "drivertable";
    public static final String COL_did = "did";
    public static final String COL_dname = "name";
    public static final String COL_daddress = "address";
    public static final String COL_dage = "age";
    public static final String COL_dgender = "gender";
    public static final String COL_dcharge = "charge";
    public static final String COL_dbadges = "badges";
    public static final String COL_dlocation = "location";
    public static final String COL_dyoe = "yearofexperience";
    public static final String COL_dphno = "phno";
    public static final String COL_demail = "email";
    public static final String COL_v = "v";
    public static final String COL_uid = "_id";

    //create table for conformation
    public static final String TABLE_NOTIFICATION = "NOTIFICATION";
    public static final String COL_nid = "nid";
    public static final String COL_ploc = "ploc";
    public static final String COL_dloc = "dloc";
    public static final String COL_pdate = "pdate";
    public static final String COL_ddate = "ddate";
    public static final String COL_nuid = "nuid";
    public static final String COL_time = "time";
    public static final String COL_username = "username";
    public static final String COL_scid = "scid";
    public static final String COL_carname = "cname";
    public static final String COL_status = "status";


    //create table for driver conformation
    public static final String TABLE_DNOTIFICATION = "DNOTIFICATION";
    public static final String COL_dnid = "dnid";
    public static final String COL_dploc = "dploc";
    public static final String COL_ddloc = "ddloc";
    public static final String COL_dpdate = "dpdate";
    public static final String COL_dddate = "dddate";
    public static final String COL_dnuid = "dnuid";
    public static final String COL_dtime = "dtime";
    public static final String COL_dusername = "dusername";
    public static final String COL_dscid = "dcid";
    public static final String COL_drivername = "dname";
    public static final String COL_dstatus = "dstatus";


    //table car review

    public static final String TABLE_CREVIEW = "CREVIEW";
    public static final String COL_rid = "_id";
    public static final String COL_carid = "rcid";
    public static final String COL_user = "user";
    public static final String COL_creview = "creview";
    public static final String COL_rtime = "rtime";


    //table driver review

    public static final String TABLE_DREVIEW = "DREVIEW";
    public static final String COL_rdid = "_id";
    public static final String COL_driverid = "rdid";
    public static final String COL_duser = "user";
    public static final String COL_dreview = "dreview";
    public static final String COL_drtime = "dtime";



    // create  user table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_2 + " TEXT,"
            + COL_3 + " TEXT,"
            + COL_4 + " TEXT,"
            + COL_5 + " TEXT,"
            + COL_6 + " TEXT,"
            + COL_7 + " TEXT,"
            + COL_8 + " TEXT" + ")";

    //CREATE TABLE DATE
    private String CREATE_DATE_TABLE = "CREATE TABLE " + TABLE_DATE + "("
            + COL_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_pick + " TEXT,"
            + COL_drop + " TEXT"
            + ")";
    // CREATE CAR TABLE

    private String CREATE_CAR_TABLE = "CREATE TABLE " + TABLE_CAR + "("
            + COL_cid + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_cbrand + " TEXT,"
            + COL_cmodel + " TEXT,"
            + COL_camount + " TEXT,"
            + COL_cagency + " TEXT,"
            + COL_ckms + " TEXT,"
            + COL_cphone + " TEXT,"
            + COL_cloc + " TEXT,"
            + COL_cemail + " TEXT,"
            + COL_cuid + " TEXT,"
            + COL_iv + " BLOB,"
            + " FOREIGN KEY ("+COL_cuid+") REFERENCES "+TABLE_NAME+" ("+COL_1+"));";

// CREATE DRIVER TABLE

    private String CREATE_DRIVER_TABLE = "CREATE TABLE " + TABLE_DRIVER + "("
            + COL_did + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_dname + " TEXT,"
            + COL_daddress+ " TEXT,"
            + COL_dage + " TEXT,"
            + COL_dgender + " TEXT,"
            + COL_dcharge + " TEXT,"
            + COL_dbadges + " TEXT,"
            + COL_dlocation + " TEXT,"
            + COL_dyoe + " TEXT,"
            + COL_dphno + " TEXT,"
            + COL_demail + " TEXT,"
            + COL_uid + " TEXT,"
            + COL_v + " BLOB,"
            + " FOREIGN KEY ("+COL_uid+") REFERENCES "+TABLE_NAME+" ("+COL_1+"));";

    //CREATE TBALE NOTIFACTION

    private String CREATE_NOTIFICATION_TABLE = "CREATE TABLE " + TABLE_NOTIFICATION + "("
            + COL_nid + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_username + " TEXT,"
            + COL_ploc + " TEXT,"
            + COL_dloc + " TEXT,"
            + COL_pdate + " TEXT,"
            + COL_ddate + " TEXT,"
            + COL_time + " date default CURRENT_TIMESTAMP, "
            + COL_nuid + " TEXT,"
            + COL_scid + " INTEGER,"
            + COL_carname + " TEXT NOT NULL,"
            + COL_status + " TEXT NOT NULL,"
            + " FOREIGN KEY ("+COL_scid+") REFERENCES "+TABLE_CAR+" ("+COL_cid+"));";


    //CREATE TBALE DNOTIFACTION

    private String CREATE_DNOTIFICATION_TABLE = "CREATE TABLE " + TABLE_DNOTIFICATION + "("
            + COL_dnid + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_dusername + " TEXT,"
            + COL_dploc + " TEXT,"
            + COL_ddloc + " TEXT,"
            + COL_dpdate + " TEXT,"
            + COL_dddate + " TEXT,"
            + COL_dtime + " date default CURRENT_TIMESTAMP, "
            + COL_dnuid + " TEXT,"
            + COL_dscid + " TEXT,"
            + COL_drivername + " TEXT,"
            + COL_dstatus + " TEXT,"
            + " FOREIGN KEY ("+COL_dscid+") REFERENCES "+TABLE_DRIVER+" ("+COL_did+"));";

    //create table car review

    private String CREATE_CREVIEW = "CREATE TABLE " + TABLE_CREVIEW + "("
            + COL_rid + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_carid + " TEXT,"
            + COL_user + " TEXT,"
            + COL_rtime + " date default CURRENT_TIMESTAMP, "
            + COL_creview + " TEXT" + ")";


    //create table driver review

    private String CREATE_DREVIEW = "CREATE TABLE " + TABLE_DREVIEW + "("
            + COL_rdid + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_driverid + " TEXT,"
            + COL_duser + " TEXT,"
            + COL_drtime + " date default CURRENT_TIMESTAMP, "
            + COL_dreview + " TEXT" + ")";



    public DatabaseHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, 3


        );

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_DATE_TABLE);
        db.execSQL(CREATE_CAR_TABLE);
        db.execSQL(CREATE_DRIVER_TABLE);
        db.execSQL(CREATE_NOTIFICATION_TABLE);
        db.execSQL(CREATE_DNOTIFICATION_TABLE);
        db.execSQL(CREATE_CREVIEW);
        db.execSQL(CREATE_DREVIEW);
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRIVER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DNOTIFICATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CREVIEW);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DREVIEW);
        onCreate(db);

    }
    public Cursor driverlist(String _id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_DRIVER + " WHERE " + COL_uid + " = '" + _id + "'", null);

        return cursor;
    }


    public Cursor carlist(String _id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CAR + " WHERE " + COL_cuid + " = '" + _id + "'", null);

        return cursor;
    }

    //***&&&&&&&&&&& delete list of car booking&&&&&&&&&&&***********
    public Cursor dellist(String nuid) {
        SQLiteDatabase db = getReadableDatabase();

        String POSTS_SELECT_QUERY =
                String.format("SELECT * FROM %s JOIN %s ON %s.%s = %s.%s WHERE %s = "+nuid,
                        TABLE_NOTIFICATION,
                        TABLE_NAME,
                        TABLE_NOTIFICATION, COL_nuid,
                        TABLE_NAME, COL_1,
                        COL_nuid);

        // return cursor;
        Cursor cursor = db.rawQuery(POSTS_SELECT_QUERY, null);
        if (cursor != null) {

            cursor.moveToLast();
        }
        return cursor;
    }


    //***&&&&&&&&&&& delete list of driver booking&&&&&&&&&&&***********
    public Cursor dellist2(String nuid) {
        SQLiteDatabase db = getReadableDatabase();

        String POSTS_SELECT_QUERY =
                String.format("SELECT * FROM %s JOIN %s ON %s.%s = %s.%s WHERE %s = "+nuid,
                        TABLE_DNOTIFICATION,
                        TABLE_NAME,
                        TABLE_DNOTIFICATION, COL_dnuid,
                        TABLE_NAME, COL_1,
                        COL_dnuid);

        // return cursor;
        Cursor cursor = db.rawQuery(POSTS_SELECT_QUERY, null);
        if (cursor != null) {

            cursor.moveToLast();
        }
        return cursor;
    }


    public Cursor notiflist(String _id) {
        SQLiteDatabase db = getReadableDatabase();

       // Cursor cursor = db.rawQuery
        String POSTS_SELECT_QUERY =
                String.format("SELECT * FROM %s JOIN %s ON %s.%s = %s.%s WHERE %s = "+_id,
        TABLE_NOTIFICATION,
                TABLE_CAR,
                TABLE_NOTIFICATION, COL_scid,
                TABLE_CAR, COL_cid,
                COL_cuid);

       // return cursor;
        Cursor cursor = db.rawQuery(POSTS_SELECT_QUERY, null);
        if (cursor != null) {

            cursor.moveToLast();
        }
        return cursor;

    }



    public Cursor dnotiflist(String _id) {
        SQLiteDatabase db = getReadableDatabase();

        // Cursor cursor = db.rawQuery
        String POSTS_SELECT_QUERY =
                String.format("SELECT * FROM %s JOIN %s ON %s.%s = %s.%s WHERE %s = "+_id,
                        TABLE_DNOTIFICATION,
                        TABLE_DRIVER,
                        TABLE_DNOTIFICATION, COL_dscid,
                        TABLE_DRIVER, COL_did,
                        COL_uid);

        // return cursor;
        Cursor cursor = db.rawQuery(POSTS_SELECT_QUERY, null);
        if (cursor != null) {

            cursor.moveToLast();
        }
        return cursor;
    }

    //****************review list***********
    public Cursor crlist(String _id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CREVIEW + " WHERE " + COL_carid + " = '" + _id + "'", null);

        return cursor;
    }


    //************************review list driver*************

    public Cursor drlist(String _id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_DREVIEW + " WHERE " + COL_driverid + " = '" + _id + "'", null);

        return cursor;
    }


        public boolean insertdata(String username, String address, String gender, String email, String phnno, String password, String location) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, address);
        contentValues.put(COL_4, gender);
        contentValues.put(COL_5, email);
        contentValues.put(COL_6, phnno);
        contentValues.put(COL_7, password);
        contentValues.put(COL_8, location);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }



        //inserting data to date table

    public boolean insertdatedata(String datepick, String datedrop) {

        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_pick, datepick);
        contentValues.put(COL_drop, datedrop);

        long result = db2.insert(TABLE_DATE, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }



    //insert car review data

    public boolean creview( String carid2,String username, String rating) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_carid, carid2);
        contentValues.put(COL_user, username);
        contentValues.put(COL_creview, rating);




        long result = db.insert(TABLE_CREVIEW, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }



    //*************insert driver review data***************

    public boolean dreview( String driverid2,String username, String rating) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_driverid, driverid2);
        contentValues.put(COL_duser, username);
        contentValues.put(COL_dreview, rating);




        long result = db.insert(TABLE_DREVIEW, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

    //inserting data to notification table

    public boolean insertnotif( String username,String spickloc, String sdroploc,String spdate, String sddate,String uid,String scid,String cname,String status) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_username, username);
        contentValues.put(COL_ploc, spickloc);
        contentValues.put(COL_dloc, sdroploc);
        contentValues.put(COL_pdate, spdate);
        contentValues.put(COL_ddate, sddate);
        contentValues.put(COL_nuid, uid);
        contentValues.put(COL_scid, scid);
        contentValues.put(COL_carname, cname);
        contentValues.put(COL_status, status);



        long result = db.insert(TABLE_NOTIFICATION, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }


    //inserting data to DRTIVER notification table

    public boolean insertdnotif( String username,String spickloc, String sdroploc, String spdate, String
            sddate,String uid, String scid,String dname,String dstatus) {

        SQLiteDatabase dbd = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_dusername, username);
        contentValues.put(COL_dploc, spickloc);
        contentValues.put(COL_ddloc, sdroploc);
        contentValues.put(COL_dpdate, spdate);
        contentValues.put(COL_dddate, sddate);
        contentValues.put(COL_dnuid, uid);

        contentValues.put(COL_dscid, scid);
        contentValues.put(COL_drivername, dname);
        contentValues.put(COL_dstatus, dstatus);


        long result = dbd.insert(TABLE_DNOTIFICATION, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }


    //INSERT CAR DATA

    public boolean insertcardata(String cbrand, String cmodel, String camount, String cagency, String ckms, String cphone, String cloc, String cemail,String uid, byte[] img) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_cbrand, cbrand);
        contentValues.put(COL_cmodel, cmodel);
        contentValues.put(COL_camount, camount);
        contentValues.put(COL_cagency, cagency);
        contentValues.put(COL_ckms, ckms);
        contentValues.put(COL_cphone, cphone);
        contentValues.put(COL_cloc, cloc);
        contentValues.put(COL_cemail, cemail);
        contentValues.put(COL_cuid, uid);
        contentValues.put(COL_iv, img);

        long result = db.insert(TABLE_CAR, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

    public byte[] carImage(String _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] col=new String[]{COL_iv};  // your column which data u want to retrive if id is same
        Cursor c=db.query(TABLE_CAR, col, COL_cid+"="+_id,null, null, null, null);
        if (c.moveToFirst()) {
            byte[] blob = c.getBlob(c.getColumnIndex(COL_iv));
            c.close();
            return blob;
        }
        c.close();
        return null;
    }

    public byte[] driverImage(String _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] col=new String[]{COL_v};  // your column which data u want to retrive if id is same
        Cursor c=db.query(TABLE_DRIVER, col, COL_did+"="+_id,null, null, null, null);
        if (c.moveToFirst()) {
            byte[] blob = c.getBlob(c.getColumnIndex(COL_v));
            c.close();
            return blob;
        }
        c.close();
        return null;
    }

    public boolean insertdriverdata(String dname, String daddress, String dage, String dgender, String dcharge, String dbadge,  String dlocation,String dyoe,String dphno,String demail,String uid, byte[] img) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_dname, dname);
        contentValues.put(COL_daddress, daddress);
        contentValues.put(COL_dage, dage);
        contentValues.put(COL_dgender, dgender);
        contentValues.put(COL_dcharge, dcharge);
        contentValues.put(COL_dbadges, dbadge);
        contentValues.put(COL_dlocation, dlocation);
        contentValues.put(COL_dyoe, dyoe);
        contentValues.put(COL_dphno, dphno);
        contentValues.put(COL_demail, demail);
        contentValues.put(COL_uid, uid);
        contentValues.put(COL_v, img);
        long result = db.insert(TABLE_DRIVER, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

     //update driver data
    public int updatedriverdata(String did, String dname, String daddress, String dage, String dgender, String dcharge, String dbadge, String dlocation, String dyoe, String dphno,String demail,String uid, byte[] img) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_did, did);
        contentValues.put(COL_dname, dname);
        contentValues.put(COL_daddress, daddress);
        contentValues.put(COL_dage, dage);
        contentValues.put(COL_dgender, dgender);
        contentValues.put(COL_dcharge, dcharge);
        contentValues.put(COL_dbadges, dbadge);
        contentValues.put(COL_dlocation, dlocation);
        contentValues.put(COL_dyoe, dyoe);
        contentValues.put(COL_dphno, dphno);
        contentValues.put(COL_demail, demail);
        contentValues.put(COL_uid, uid);
        contentValues.put(COL_v, img);
        int i = db.update(TABLE_DRIVER, contentValues, COL_did + " = " + did, null);
        return i;
    }

//********************update staus of car*****************

    public int ustatus(String did, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_status, status);
        int i = db.update(TABLE_NOTIFICATION, contentValues, COL_scid + " = " + did, null);
        return i;
    }

    //********************update staus of driver*****************

    public int udstatus(String did, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_dstatus, status);
        int i = db.update(TABLE_DNOTIFICATION, contentValues, COL_dscid + " = " + did, null);
        return i;
    }


    //delete cardata
    public int deletecardata(String cid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_cid, cid);
        int i = db.delete(TABLE_CAR, COL_cid + " = " + cid, null);
        return i;
    }

    //delete driverdata
    public int deletedriverdata(String did){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_did, did);
        int i = db.delete(TABLE_DRIVER, COL_did + " = " + did, null);
        return i;
    }

    //updatecardat

    public int updatecardata(String cid,String cbrand, String cmodel, String camount, String cagency, String ckms, String cphone, String cloc, String cemail,String uid, byte[] img) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_cid, cid);
        contentValues.put(COL_cbrand, cbrand);
        contentValues.put(COL_cmodel, cmodel);
        contentValues.put(COL_camount, camount);
        contentValues.put(COL_cagency, cagency);
        contentValues.put(COL_ckms, ckms);
        contentValues.put(COL_cphone, cphone);
        contentValues.put(COL_cloc, cloc);
        contentValues.put(COL_cemail, cemail);
        contentValues.put(COL_cuid, uid);
        contentValues.put(COL_iv, img);
        int i = db.update(TABLE_CAR, contentValues, COL_cid + " = " + cid, null);
        return i;

    }

    public Cursor updatedriver() {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[] { COL_did,COL_dname,COL_daddress,COL_dage,COL_dgender,COL_dcharge,COL_dbadges,COL_dlocation,COL_dyoe,COL_dphno,COL_demail,COL_uid,COL_v };
        Cursor cursor = db.query(TABLE_DRIVER, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public UserModel Authenticate(UserModel userModel) {

        // array of columns to fetch
        String[] columns = {
                COL_1,
                COL_2,
                COL_3,
                COL_4,
                COL_5,
                COL_6,
                COL_7,
                COL_8
        };
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, //Table to query
                columns,                    //columns to return
                null,                  //columns for the WHERE clause
                null,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            do {

                UserModel user1 = new UserModel(cursor.getString(cursor.getColumnIndex(this.COL_1)),
                        cursor.getString(cursor.getColumnIndex(this.COL_2)),
                        cursor.getString(cursor.getColumnIndex(this.COL_3)),
                        cursor.getString(cursor.getColumnIndex(this.COL_4)),
                        cursor.getString(cursor.getColumnIndex(this.COL_5)),
                        cursor.getString(cursor.getColumnIndex(this.COL_6)),
                        cursor.getString(cursor.getColumnIndex(this.COL_7)),
                        cursor.getString(cursor.getColumnIndex(this.COL_8)));

                if (userModel.password.equalsIgnoreCase(user1.password) && userModel.username.equals(user1.username)) {
                    return user1;

                }
            } while (cursor.moveToNext());

        }
        cursor.close();
        return null;
    }


    public Cursor singleUser(String _id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COL_1+" = '"+_id+"'",null);

        return  cursor;
    }
    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }


}

