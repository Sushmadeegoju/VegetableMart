package com.example.sushma.vegetablemart.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.sushma.vegetablemart.Pozos.CartPozo;

import java.util.ArrayList;
import java.util.List;

import static com.example.sushma.vegetablemart.LoginActivity.sp;

public class CartDatabase extends SQLiteOpenHelper {
    public static final String TBNAME = "Cart";
    public static final String ID = "id";
    public static final String NAME = "Name";
    public static final String QUANTITY = "Quantity";
    public static final String COST = "Cost";
    public static final String PRICE = "Price";
    public static final String IMAGE = "Image";
    public static final String MOBILE = "Mobile";
    SQLiteDatabase db;

    public CartDatabase(Context context) {
        super(context, TBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table cart" + "(id integer ,Name text primary key ,Quantity text,Cost text,Price text,Image integer,Mobile text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void insertData(CartPozo pozo) {
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, pozo.getName());
        values.put(QUANTITY, pozo.getQuantity());
        values.put(COST, pozo.getCost());
        values.put(PRICE, pozo.getAmount());
        values.put(IMAGE, pozo.getImage());
        pozo.setMobile(sp.getString("mobile", null));
        values.put(MOBILE,pozo.getMobile());

        db.insert(TBNAME, null, values);
    }

    public List<CartPozo> getData() {
        List<CartPozo> list = new ArrayList<>();
        db = getReadableDatabase();
        String mobile = sp.getString("mobile", null);
        String selectQuery = "SELECT  * FROM " + TBNAME + " WHERE " + MOBILE + "='" + mobile + "' ";
        Cursor cursor;
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                CartPozo pozo = new CartPozo();
                pozo.setImage(cursor.getInt(5));
                pozo.setAmount(Double.valueOf(cursor.getString(4)));
                pozo.setCost(cursor.getString(3));
                pozo.setQuantity(cursor.getString(2));
                pozo.setName(cursor.getString(1));
                list.add(pozo);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public double getTotalAmount() {
        double totalAmount = 0;
        db = getReadableDatabase();
        String mobile = sp.getString("mobile", null);
        Cursor cursor = db.rawQuery("select Cost from Cart" + " WHERE " + MOBILE + " = '" + mobile + "'", null);
        if (cursor.moveToFirst()) {
            do {
                totalAmount += Double.parseDouble(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return totalAmount;
    }

    public void deleteAll() {
        db = getWritableDatabase();
        db.delete(TBNAME, null, null);
    }

    public void deleteSingleRecord(String name) {
        db = getWritableDatabase();
        db.delete(TBNAME, NAME + "=?", new String[]{name});
    }

    public void deleteSingleVendor(String mobile,String name) {
        db = getWritableDatabase();
        db.delete(TBNAME, "Mobile=? and Name=?", new String[]{mobile,name});
    }

    public boolean check(String name) {

        db = getReadableDatabase();
        boolean b = false;
        Cursor cursor = db.rawQuery("select Name from Cart" + " WHERE " + NAME + " = '" + name + "'", null);
        if (cursor.moveToFirst()) {
            do {
                String str = cursor.getString(0);
                if (name.equals(str)) {
                    b = true;
                }
            } while (cursor.moveToNext());
        }
        return b;
    }

    public List<CartPozo> getVendorDetails(){
        List<CartPozo> list = new ArrayList<>();
        db = getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TBNAME ;
        Cursor cursor;
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                CartPozo pozo = new CartPozo();
                pozo.setMobile(cursor.getString(6));
                pozo.setCost(cursor.getString(3));
                pozo.setQuantity(cursor.getString(2));
                pozo.setName(cursor.getString(1));
                list.add(pozo);
            } while (cursor.moveToNext());
        }
        return list;
    }
}


    //public void update(String name,String cost,String quantity){
    /*    db = getWritableDatabase();

        Cursor cursor = db.rawQuery("select Name,Cost,Quantity from Cart"+" WHERE "+ NAME +" = '"+name+"'", null);
        if (cursor.moveToFirst()) {
            do {
                String str=cursor.getString(0);
                if(name.equals(str)){

                }
            } while (cursor.moveToNext());
        }
        return b;
    }
}*/
