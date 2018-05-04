package com.example.lukeboyde.projectofficial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by lukeboyde on 21/04/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 7);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("Create table user(Id INTEGER PRIMARY KEY,firstname TEXT, lastname TEXT, email TEXT,password TEXT)");
         db.execSQL("Create table userInterests(Id INTEGER PRIMARY KEY, interest TEXT)");
       // db.execSQL("Create table user(email text primary key,password text)");
       // db.execSQL("Create table userInterests(interest text primary key)");
//        db.execSQL("Create table interest(interest text primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists userInterests");
        db.execSQL("drop table if exists user");

        onCreate(db);
    }

    //insert into database
        public boolean insert(Integer Id, String firstname, String lastname, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id", Id);
        contentValues.put("firstname", firstname);
        contentValues.put("lastname", lastname);
        contentValues.put("email", email);
        contentValues.put("password", password);
            long ins = db.insert("user", null, contentValues);
        if (ins == -1) return false;
        else return true;

        }
//    public boolean insert(String email, String password) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("email", email);
//        contentValues.put("password", password);
//        long ins = db.insert("user", null, contentValues);
//        if (ins == -1) return false;
//        else return true;
   // }

    //insert interest into database
    public boolean insertInterest(String interest) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log.d("project", "Interest=" + interest);
        contentValues.put("interest", interest);
        long ins = db.insert("userInterests", null, contentValues);
        Log.d("project", "ID of interest: " + ins);
        if (ins == -1) return false;
        else return true;
    }

    //checking if email exists
    public Boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?", new String[]{email});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    //checking email and password
    public Boolean emailpassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and password=?", new String[]{email, password});
        if (cursor.getCount() > 0) return true;
        else return false;
    }

    public ArrayList<NewProfile> generateProfile() {

        ArrayList<NewProfile> profile = new ArrayList<NewProfile>();
        Log.d("project", "generateProfile()");

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user",
                new String[]{});


        // moves to first result
        if (cursor.moveToFirst()) {
            //while loop will traverse till last record of cursor
            while (!cursor.isAfterLast()) {
                NewProfile np = new NewProfile
                        (cursor.getString(cursor.getColumnIndex("firstname")),
                                cursor.getString(cursor.getColumnIndex("lastname")));
                Log.d("project", "generateRecommendations()");



                profile.add(np);
                cursor.moveToNext();

            }
        }
        cursor.close();
        return profile;
    }

    public ArrayList<Interest> generateRecommendations(String interest) {

        ArrayList<Interest> interests = new ArrayList<Interest>();

        Log.d("project", "generateRecommendations()");
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM userInterests WHERE interest=?",
                new String[] {interest}); // WHERE interest='foot",
        //  new String[]{interest});

        if (cursor.moveToFirst()) {
            Log.d("project", "Found at least one interest");
            while (!cursor.isAfterLast()) {
                Log.d("project", "Interest=" + cursor.getString(cursor.getColumnIndex("interest")));
                Interest i = new Interest
                        (cursor.getString(cursor.getColumnIndex("interest")));
                interests.add(i);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return interests;


//        public ArrayList<Interest> search (String searchTerm) {
//            String sql = "SELECT * FROM " + users + " u " + " JOIN " + userInterests + " s "
//        }


//    public ArrayList<Interest> generateRecommendations() {
//        String interest = "football";
//        ArrayList<Interest> interests = new ArrayList<Interest>();
//
//        Log.d("project", "generateRecommendations()");
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM userInterests", null); // WHERE interest='foot",
//              //  new String[]{interest});
//
//        if (cursor.moveToFirst()) {
//            Log.d("project", "Found at least one interest");
//            while (!cursor.isAfterLast()) {
//                Log.d("project", "Interest=" + cursor.getString(cursor.getColumnIndex("interest")));
//                Interest i = new Interest
//                        (cursor.getString(cursor.getColumnIndex("interest")));
//                interests.add(i);
//                cursor.moveToNext();
//            }
//        }
//        return interests;




    }
}
