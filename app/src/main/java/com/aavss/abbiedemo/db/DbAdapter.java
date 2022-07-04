package com.aavss.abbiedemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.aavss.abbiedemo.util.Utility;

public class DbAdapter {
    /*TODO : DATABASE NAME*/
    public static final String DBNAME = "_abbie_demo";

    /* TODO : DATABASE VERSION */
    public static final int DBVERSION = 1;

    /*TODO : Table name*/
    public static final String TABLENAME = "abbie";

    /* TODO : Table column name*/;
    public static final String ROWID = "RowId";
    public static final String FNAME = "FName";
    public static final String LNAME = "LName";
    public static final String MOBILE = "Mobile";
    public static final String ADDRESS = "Address";

    private MyDBHelper myDBHelper;
    private SQLiteDatabase sqLiteDatabase;

    private String SQL = "CREATE TABLE "+ TABLENAME +"("+ROWID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+FNAME+" text, "+LNAME+" text, "+MOBILE+" text, "+ADDRESS+" text)";

    public DbAdapter(@Nullable Context context) {
        myDBHelper = new MyDBHelper(context);
    }

    public DbAdapter openDatabase(){
        sqLiteDatabase = myDBHelper.getWritableDatabase();
        return this;
    }

    // TODO : method to insert data into DB
    public void insertData(Context context, String fName, String lName, String mobile, String address){
        ContentValues contentValues = new ContentValues();
        contentValues.put(FNAME, fName);
        contentValues.put(LNAME, lName);
        contentValues.put(MOBILE, mobile);
        contentValues.put(ADDRESS, address);
        long id = sqLiteDatabase.insert(TABLENAME, null, contentValues);
        if (id == -1){
            Utility.toast(context, "Insertion falid.");
        }else {
            Utility.toast(context, "Data saved successfully");
        }

    }

    // TODO : method to retrieve data from DB
    public Cursor getAllData(){
        String[] columns = {ROWID, FNAME, LNAME, MOBILE, ADDRESS};
        return sqLiteDatabase.query(TABLENAME, columns, null, null, null, null, null);
    }

    //TODO : Delete single record
    public void deleteSingleRecord(Context context, String rowId){
        int deletedRow = sqLiteDatabase.delete(TABLENAME, ROWID+"="+rowId, null);
        if (deletedRow > 0){
            Utility.toast(context, ""+deletedRow +" Data deleted successfully.");
        }else {
            Utility.toast(context, "Deletion failed.");
        }
    }

    class MyDBHelper extends SQLiteOpenHelper {
        public MyDBHelper(@Nullable Context context) {
            super(context, DBNAME, null, DBVERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            //TODO : need to implement
        }
    }
}
