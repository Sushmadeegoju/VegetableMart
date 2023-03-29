package com.example.sushma.vegetablemart.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sushma.vegetablemart.Pozos.CartPozo;
import com.example.sushma.vegetablemart.Pozos.SignUpPozo;

import java.util.ArrayList;
import java.util.List;

import static com.example.sushma.vegetablemart.LoginActivity.sp;

public class SignUpDatabase extends SQLiteOpenHelper{
    public static final String DBNAME="SignUp";
    public static final String TBNAME="UserDetails";
    public static final String FNAME="Fname";
    public static final String LNAME="Lname";
    public static final String EMAIL="Email";
    public static final String MOBILE="Mobile";
    public static final String PASS="Password";
    public static final String CONFIRMPASS="ConfirmPassword";
    public static final String STATE="State";
    public static final String CITY="City";
    public static final String ADDRESS="Address";
    SQLiteDatabase db;

    public SignUpDatabase(Context context) {
        super(context, TBNAME, null,1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table UserDetails"+"(id integer primary key,Fname text,Lname text,Email text,Mobile text,Password text,ConfirmPassword text,State text,City text,Address text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void insertData(SignUpPozo pozo) {
        db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(FNAME,pozo.getFname());
        values.put(LNAME,pozo.getLname());
        values.put(EMAIL,pozo.getEmail());
        values.put(MOBILE,pozo.getMobile());
        values.put(PASS,pozo.getPass());
        values.put(CONFIRMPASS,pozo.getConfirmpass());
        values.put(STATE,pozo.getState());
        values.put(CITY,pozo.getCity());
        values.put(ADDRESS,pozo.getAddress());
        db.insert(TBNAME,null,values);
    }

    public boolean loginCheck(String mobile,String password){
        db=getReadableDatabase();
        boolean b=false;
        String selectQuery="SELECT  Mobile,Password FROM " + TBNAME + " WHERE " + MOBILE + "='" + mobile +"' AND " + PASS +"='"+ password +"'";
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                String Mobile=cursor.getString(0);
                String Password=cursor.getString(1);
               if(Mobile.equals(mobile) && Password.equals(password)){
                   b=true;
               }
            }while (cursor.moveToNext());
        }
        return b;
    }

    public boolean getMobile(String umobile) {
        Boolean b=false;
        db=getReadableDatabase();
        Cursor cursor=db.rawQuery("select Mobile from UserDetails",null);
        if(cursor.moveToFirst()){
            do{
                String mb=cursor.getString(0);
                if(mb.equals(umobile)){
                    b=true;
                }
            }while(cursor.moveToNext());
        }
        return b;
    }

    public String getPassword(String umobile) {
        String str=null;
        db=getReadableDatabase();
        Cursor cursor=db.rawQuery("select Mobile,Password from UserDetails",null);
        if(cursor.moveToFirst()){
            do{
                String mb=cursor.getString(0);
                String pass=cursor.getString(1);
                if(mb.equals(umobile)){
                    str=pass;
                }
            }while(cursor.moveToNext());
        }
        return str;
    }

    public List<SignUpPozo> getData() {
        List<SignUpPozo> list = new ArrayList<>();
        db = getReadableDatabase();
        String mobile = sp.getString("mobile", null);
        String selectQuery = "SELECT  * FROM " + TBNAME + " WHERE " + MOBILE + "='" + mobile + "' ";
        Cursor cursor;
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                SignUpPozo pozo = new SignUpPozo();
                pozo.setFname(cursor.getString(1));
                pozo.setLname(cursor.getString(2));
                pozo.setEmail(cursor.getString(3));
                pozo.setMobile(cursor.getString(4));
                pozo.setCity(cursor.getString(7));
                pozo.setState(cursor.getString(8));
                pozo.setAddress(cursor.getString(9));
                list.add(pozo);
            } while (cursor.moveToNext());
        }
        return list;
    }
}
