package com.example.sushma.vegetablemart.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sushma.vegetablemart.Pozos.CartPozo;
import com.example.sushma.vegetablemart.Pozos.OrdersPojo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.sushma.vegetablemart.LoginActivity.sp;

public class OrdersDatabase extends SQLiteOpenHelper {
    Context context;
    SQLiteDatabase db;
    Calendar calendar;
    CartPozo pozo;
    CartDatabase cartDatabase;
    public static final String TBNAME="Orders";

    public static final String DATE="Date";
    public static final String TOTALAMOUNT="TotalAmount";
    public static final String MOBILE="Mobile";
    public OrdersDatabase(Context context) {
        super(context, TBNAME, null, 1);
        calendar=Calendar.getInstance();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Orders"+"(id Integer primary key,Date text,TotalAmount text,Mobile text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
    public void insertData(String amount){
        db=getWritableDatabase();
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        int month=calendar.get(Calendar.MONTH);
        int year=calendar.get(Calendar.YEAR);
        int hours=calendar.get(Calendar.HOUR_OF_DAY);
        int mins=calendar.get(Calendar.MINUTE);
        String date=""+day+"/"+(month+1)+"/"+year+"    "+hours+" hrs:"+mins+" mins";
        ContentValues values=new ContentValues();

        values.put(TOTALAMOUNT,amount);
        values.put(MOBILE,sp.getString("mobile",null));
        values.put(DATE,date);
       db.insert(TBNAME,null,values);
    }

    public List<OrdersPojo> getData(){
        db = getReadableDatabase();

        List<OrdersPojo> tlist=new ArrayList<>();
        String mobile = sp.getString("mobile", null);

        Cursor cursor = db.rawQuery("select TotalAmount,Date from Orders" + " WHERE " + MOBILE + " = '" + mobile + "'", null);
        if (cursor.moveToFirst()) {
            do {
                    OrdersPojo pojo=new OrdersPojo();
                    pojo.setAmount(cursor.getString(0));
                    pojo.setDate(cursor.getString(1));
                    tlist.add(pojo);

            } while (cursor.moveToNext());
        }
        return tlist;
    }

    public void deleteAll() {
        db = getWritableDatabase();
        db.delete(TBNAME, null, null);
    }
}
