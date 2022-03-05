package com.example.hellopanchayat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelp extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DB_MyPanchayat";
    public static final String TABLE_Registration = "users_reg";
    public static final String COL_1 = "Name";
    public static final String COL_2 = "Email";
    public static final String COL_3 = "Password";
    public static final String COL_4 = "DOB";
    public static final String COL_5 = "Gender";
    public static final String COL_6 = "PL";
    public static final String COL_7 = "Category";
    public static final String COL_8 = "Address";
    public static final String COL_9 = "Religion";
    public static final String COL_10 = "Phone";
    public static final String COL_11 = "AIncome";
    public static final String COL_12 = "Status";

    public static final String TABLE_Scheme = "schemes";
    public static final String COL1 = "S_ID";
    public static final String COL2 = "S_Name";
    public static final String COL3 = "S_Cat";
    public static final String COL4 = "S_Eligty";
    public static final String COL5 = "Documents";
    public static final String COL6 = "S_ld";

    public static final String TABLE_Application = "applications";
    public static final String A_COL1 = "AID";
    public static final String A_COL2 = "UID";
    public static final String A_COL3 = "SID";
    public static final String A_COL4 = "Status";


    public DBhelp(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_Registration + " ("
                + COL_1 + " TEXT NOT NULL,"
                + COL_2 + " TEXT PRIMARY KEY NOT NULL,"
                + COL_3 + " TEXT NOT NULL,"
                + COL_4 + " TEXT NOT NULL,"
                + COL_5 + " TEXT NOT NULL,"
                + COL_6 + " TEXT NOT NULL,"
                + COL_7 + " TEXT NOT NULL,"
                + COL_8 + " TEXT NOT NULL,"
                + COL_9 + " TEXT NOT NULL,"
                + COL_10 + " INTEGER NOT NULL,"
                + COL_11 + " INTEGER NOT NULL,"
                + COL_12 + " TEXT NOT NULL)";

        String squery = "CREATE TABLE "+ TABLE_Scheme + "("
                + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL2 + " TEXT,"
                + COL3 + " TEXT,"
                + COL4 + " TEXT,"
                + COL5 + " TEXT,"
                + COL6 + " TEXT )";

        String aquery = "CREATE TABLE "+ TABLE_Application + "("
                + A_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + A_COL2 + " TEXT,"
                + A_COL3 + " INTEGER,"
                + A_COL4 + " TEXT,"
                + " FOREIGN KEY ("+A_COL2+") REFERENCES "+TABLE_Registration+"("+COL_1+"),"
                + " FOREIGN KEY ("+A_COL3+") REFERENCES "+TABLE_Scheme+"("+COL1+"));";

        db.execSQL(query);
        db.execSQL(squery);
        db.execSQL(aquery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Registration);
        onCreate(db);
    }

    public boolean insertUData(String toString, String toString1, String toString2, String toString3, String gen, String pl, String cat, String toString4, String item, String toString5, String toString6, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, toString);
        contentValues.put(COL_2, toString1);
        contentValues.put(COL_3, toString2);
        contentValues.put(COL_4, toString3);
        contentValues.put(COL_5, gen);
        contentValues.put(COL_6, pl);
        contentValues.put(COL_7, cat);
        contentValues.put(COL_8, toString4);
        contentValues.put(COL_9, item);
        contentValues.put(COL_10, toString5);
        contentValues.put(COL_11, toString6);
        contentValues.put(COL_12, status);

        long result = db.insert(TABLE_Registration, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_Registration, null);
        return res;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_Scheme, "S_ID = ?", new String[]{id});
        //db.execSQL("DROP TABLE IF EXISTS "+TABLE_Registration);
        //return 1;

    }

    public boolean loginCheck(String toString, String toString1, String st) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_Registration + " WHERE Email = ? AND Password = ? AND Status = ?",new String[]{toString,toString1,st});

        if (res.getCount() > 0 ) {
            return true;
        } else {
            return false;
        }
        //db.close();
        //cursor.close();
        //return exist;

    }

    public boolean insertSData(String toString, String item, String toString1, String toString2, String toString3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, toString);
        contentValues.put(COL3, item);
        contentValues.put(COL4, toString1);
        contentValues.put(COL5, toString2);
        contentValues.put(COL6, toString3);

        long result = db.insert(TABLE_Scheme, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public boolean AddApplication(String uid, String id, String status) {
       SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(A_COL2, uid);
        contentValues.put(A_COL3, id);
        contentValues.put(A_COL4, status);

        long result = db.insert(TABLE_Application, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
        /*SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_Application);
        return true;*/
    }
    public Cursor getSchemeData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_Scheme, null);
        return res;
    }

    public boolean ApproveUser(String user, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //cv.put(COL_2, user);
        cv.put(COL_12, status);

        db.update(TABLE_Registration,cv,"Email = ?",new String[] {user});
        return true;
    }

    public boolean UpdateScheme(String id,String toString, String item, String toString1, String toString2, String toString3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, toString);
        contentValues.put(COL3, item);
        contentValues.put(COL4, toString1);
        contentValues.put(COL5, toString2);
        contentValues.put(COL6, toString3);

        db.update(TABLE_Scheme,contentValues,"S_ID = ?",new String[] {id});
        return true;
    }

    public Cursor getSchemeDataIndividual(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_Scheme + " WHERE S_ID = ?",new String[] {id});
        return res;
    }


    public Cursor getAppData(String st) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_Application + " WHERE Status = ?",new String[] {st});
        return res;
    }
    /*public Cursor getAppData1(String st) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_Application + TABLE_Registration + " WHERE UID == Email AND Status = ?",new String[] {st});

        return res;
    }*/

    public boolean UpdateAPP(String aid, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //cv.put(COL_2, user);
        cv.put(A_COL4, status);

        db.update(TABLE_Application,cv,"AID = ?",new String[] {aid});
        return true;
    }

    /*public Cursor getAppDataIndividual(String aid) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_Application + " WHERE AID = ?",new String[] {aid});
        return res;
    }*/

    public Cursor getAppData1(String uid) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_Application + " WHERE UID = ?",new String[] {uid});
        return res;
    }

    public Cursor getAllData1(String usr) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_Registration + " WHERE Email = ?",new String[] {usr});
        return res;

    }
}
