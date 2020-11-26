package com.kizias.thuchanh_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SanPhamSQLite extends SQLiteOpenHelper {


    public SanPhamSQLite(@Nullable Context context) {
        super(context, "db.SanPham", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SanPhams (" +
                "masp integer primary key," +
                "tensp text," +
                "soluong integer," +
                "dongia real)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS SanPhams");
        onCreate(db);
    }

    public void AddSanPham(SanPham sp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("masp", sp.getMasp());
        values.put("tensp", sp.getTensp());
        values.put("soluong", sp.getSoluong());
        values.put("dongia", sp.getDongia());
        db.insert("SanPhams", null, values);
        db.close();
    }

    public ArrayList<SanPham> getAllSanPham() {
        ArrayList<SanPham> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SanPhams", null);
        if (cursor.moveToFirst()){
            do {
                SanPham sp = new SanPham();
                sp.setMasp(cursor.getInt(0));
                sp.setTensp(cursor.getString(1));
                sp.setSoluong(cursor.getInt(2));
                sp.setDongia(cursor.getDouble(3));
                list.add(sp);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public void DeleteSanPham(int masp) {
        SQLiteDatabase db = this.getWritableDatabase();
        // db.delete("tablename","id=? and name=?",new String[]{"1","jack"});
        db.delete("SanPhams", "masp" + "= ?", new String[]{Integer.toString(masp)});
        db.close();
    }

    public void UpdateSanPham(SanPham sp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("masp", sp.getMasp());
        values.put("tensp", sp.getTensp());
        values.put("soluong", sp.getSoluong());
        values.put("dongia", sp.getDongia());
        db.update("SanPhams", values, "masp" + "= ?", new String[]{Integer.toString(sp.getMasp())});
        db.close();
    }

    public String getPath(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.getPath();
    }
}
